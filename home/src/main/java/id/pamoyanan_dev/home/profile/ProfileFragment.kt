package id.pamoyanan_dev.home.profile

import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import id.pamoyanan_dev.home.R
import id.pamoyanan_dev.home.databinding.ProfileFragmentBinding
import id.pamoyanan_dev.l_extras.base.BaseFragment
import id.pamoyanan_dev.l_extras.data.model.MedSocSignIn
import id.pamoyanan_dev.l_extras.ext.getViewModel
import id.pamoyanan_dev.l_extras.ext.navigatorWithActivityClearTop
import id.pamoyanan_dev.l_extras.ext.putArgs
import id.pamoyanan_dev.l_extras.ext.showToast
import id.pamoyanan_dev.l_extras.util.Preference
import id.pamoyanan_dev.movieshop.AppConst.GOOGLE_SIGN_IN_DATA
import id.pamoyanan_dev.movieshop.AppNavigator.getSplashRoute
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment<ProfileFragmentBinding, ProfileVM>() {

    override fun bindLayoutRes() = R.layout.profile_fragment

    override fun onSetViewModel(): ProfileVM {
        return getViewModel { ProfileVM(MainApp.instance) }
    }

    override fun onLoadObserver(baseViewModel: ProfileVM) {
        baseViewModel.apply {
            val medSocSignInAsString = Preference.getPref(requireContext(), GOOGLE_SIGN_IN_DATA)
            val medSocSignAsObject = Gson().fromJson<MedSocSignIn>(medSocSignInAsString, MedSocSignIn::class.java)
            imageUrl.value = medSocSignAsObject.profileImage
            email.value = medSocSignAsObject.email
            fullname.value = medSocSignAsObject.fullname
        }
    }

    override fun onStartWork() {
        btn_profile_signOut.setOnClickListener {
            baseViewModel.scope.launch {
                onGoogleSignOut()
                onFacebookSignOut()
                Preference.removePref(requireContext(), GOOGLE_SIGN_IN_DATA)
                delay(1000)
                requireContext().navigatorWithActivityClearTop(getSplashRoute()) {}
                requireActivity().finish()
            }
        }
    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                profileVM = it
            }
        }
    }

    fun onGoogleSignOut() {
        FirebaseAuth.getInstance().signOut()
        googleSignInClient.signOut()
                .addOnCompleteListener(requireActivity()) {

                }
                .addOnFailureListener { exception ->
                    requireContext().showToast(exception.message
                            ?: "There is something wrong with sign out process")
                }
    }

    fun onFacebookSignOut() {
        LoginManager.getInstance().logOut()
    }

    companion object {
        fun newInstance() = ProfileFragment().putArgs { }
    }

}