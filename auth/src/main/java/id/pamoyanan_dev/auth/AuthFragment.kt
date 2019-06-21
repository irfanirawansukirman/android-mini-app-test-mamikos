package id.pamoyanan_dev.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.Gson
import id.pamoyanan_dev.auth.databinding.AuthFragmentBinding
import id.pamoyanan_dev.l_extras.base.BaseFragment
import id.pamoyanan_dev.l_extras.data.model.MedSocSignIn
import id.pamoyanan_dev.l_extras.ext.getViewModel
import id.pamoyanan_dev.l_extras.ext.navigatorImplicit
import id.pamoyanan_dev.l_extras.ext.putArgs
import id.pamoyanan_dev.l_extras.ext.showToast
import id.pamoyanan_dev.l_extras.util.Preference
import id.pamoyanan_dev.movieshop.AppConst.RC_SIGN_IN
import id.pamoyanan_dev.movieshop.AppNavigator.getHomeRoute
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONException
import id.pamoyanan_dev.movieshop.R as R2


/**
 * references :
 * https://androidclarified.com/android-facebook-login-example/ [facebook login]
 */
class AuthFragment : BaseFragment<AuthFragmentBinding, AuthVM>(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_auth_signIn -> {
                onGoogleSignOut()
                onFacebookSignOut()
            }
            R.id.btn_auth_facebook -> onFacebookSignIn()
            else -> onGoogleSignIn()
        }
    }

    override fun bindLayoutRes() = R.layout.auth_fragment

    override fun onSetViewModel(): AuthVM {
        return getViewModel { AuthVM(MainApp.instance) }
    }

    override fun onLoadObserver(baseViewModel: AuthVM) {

    }

    override fun onStartWork() {
        setupViewListener()
        setupGoogleClient(setupGoogleSignIn())
        setupFirebaseAuth()
        setupFacebookSignIn()

        // check current state if user has been login
        if (Preference.getPref(requireContext(), GOOGLE_SIGN_IN_DATA).isNotEmpty()) {
            navigateToHome()
        }
    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                authVM = it
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    firebaseAuthWithGoogle(it)
                }
            } catch (e: ApiException) {
                Log.e(AuthFragment::class.java.simpleName, "Google sign in failed", e)
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        showUserAuthProfile(it)
                    }
                } else {
                    requireContext().showToast("Authentication Failed")
                }
            }
    }

    private fun showUserAuthProfile(user: FirebaseUser) {
        GlobalScope.launch {
            btn_auth_signIn.text = user.email

            val medSocSignIn = MedSocSignIn(
                fullname = user.displayName ?: "",
                email = user.email ?: "",
                profileImage = getCurrentUser()?.photoUrl.toString(),
                phoneNumber = user.phoneNumber ?: ""
            )
            saveGoogleSignIn(medSocSignIn)

            delay(1000)
            navigateToHome()
        }
    }

    private fun saveGoogleSignIn(data: MedSocSignIn) {
        val dataAsString = Gson().toJson(data)
        Preference.savePref(requireContext(), GOOGLE_SIGN_IN_DATA, dataAsString)
    }

    private fun setupViewListener() {
        btn_auth_signIn.setOnClickListener(this)
        btn_auth_facebook.setOnClickListener(this)
        btn_auth_google.setOnClickListener(this)
    }

    private fun navigateToHome() {
        requireContext().navigatorImplicit(getHomeRoute()) {}
        activity?.finish()
    }

    private fun setupGoogleSignIn() = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R2.string.default_web_client_id))
        .requestEmail()
        .build()

    private fun setupGoogleClient(gso: GoogleSignInOptions) {
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun setupFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
    }

    private fun onGoogleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun onGoogleSignOut() {
        FirebaseAuth.getInstance().signOut()
        googleSignInClient.signOut()
            .addOnCompleteListener(requireActivity()) {
                if (getCurrentUser() == null) {
                    btn_auth_signIn.text = "Sign In"
                }
            }
            .addOnFailureListener { exception ->
                requireContext().showToast(exception.message ?: "There is something wrong with sign out process")
            }
    }

    private fun onFacebookSignOut() {
        FirebaseAuth.getInstance().signOut()
        LoginManager.getInstance().logOut()
    }

    private fun setupFacebookSignIn() {
        callbackManager = CallbackManager.Factory.create()

        btn_auth_fbOriginal.apply {
            setReadPermissions("email", "public_profile")
            fragment = this@AuthFragment
            registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException) {
                }
            })
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val request = GraphRequest.newMeRequest(
            token
        ) { facebookObj, response ->
            try {
                val name = facebookObj.getString("name")
                val email = facebookObj.getString("email")
                val profileImage = facebookObj.getJSONObject("picture").getJSONObject("data").getString("url")
                btn_auth_signIn.text = name

                val medSocSignIn = MedSocSignIn(
                    fullname = name,
                    email = email,
                    profileImage = profileImage,
                    phoneNumber = getCurrentUser()?.phoneNumber ?: ""
                )
                saveGoogleSignIn(medSocSignIn)

                GlobalScope.launch {
                    delay(1000)
                    navigateToHome()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters
        // Initiate the GraphRequest
        request.executeAsync()
    }

    private fun checkUserState() {
        if (Preference.getPrefBoolean(requireContext(), IS_GOOGLE_SELECTED)) {
            Log.d("IRFAN ", "GOOGLE")
            getCurrentUser()?.let {
                showUserAuthProfile(it)
            }
        } else {
            Log.d("IRFAN ", "FB")
            val accessToken = AccessToken.getCurrentAccessToken()
            if (accessToken != null) {
                handleFacebookAccessToken(accessToken)
            } else {
                btn_auth_signIn.text = "Sign In"
            }
        }
    }

    private fun getCurrentUser() = FirebaseAuth.getInstance().currentUser

    private fun getUserProfile() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
    }

    private fun onFacebookSignIn() {
        btn_auth_fbOriginal.performClick()
    }

    companion object {
        const val IS_GOOGLE_SELECTED = "IS_GOOGLE_SELECTED"
        const val GOOGLE_SIGN_IN_DATA = "GOOGLE_SIGN_IN_DATA"

        fun newInstance() = AuthFragment().putArgs { }
    }

}