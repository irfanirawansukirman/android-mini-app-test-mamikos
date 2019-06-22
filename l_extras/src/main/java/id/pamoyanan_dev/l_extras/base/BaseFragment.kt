package id.pamoyanan_dev.l_extras.base

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import id.pamoyanan_dev.l_extras.R
import id.pamoyanan_dev.l_extras.ext.showToast

abstract class BaseFragment<VDB : ViewDataBinding, BVM : BaseViewModel> : Fragment() {

    lateinit var viewBinding: VDB
    lateinit var baseViewModel: BVM
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var auth: FirebaseAuth
    lateinit var callbackManager: CallbackManager

    private var messageType = MESSAGE_SNACK_TYPE

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, bindLayoutRes(), container, false)

        viewBinding.let {
            it.lifecycleOwner = this@BaseFragment
        }

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel = onSetViewModel()
        baseViewModel.apply {
            eventGlobalMessage.observe(this@BaseFragment, Observer { message ->
                if (message != null) {
                    // when (messageType) {
                    // MESSAGE_SNACK_TYPE -> {
                    // show snack
                    // }
                    // else -> {
                    // show toast
                    requireContext().showToast(message)
                    // }
                    // }
                }
            })
        }

        onLoadObserver(baseViewModel)
        onSetInstrument()

        setupGoogleClient(setupGoogleSignIn())
        setupFirebaseAuth()

        onStartWork()
    }

    private fun setupGoogleSignIn() = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

    private fun setupGoogleClient(gso: GoogleSignInOptions) {
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    fun setupFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
    }

    @LayoutRes
    abstract fun bindLayoutRes(): Int

    abstract fun onSetViewModel(): BVM
    abstract fun onLoadObserver(baseViewModel: BVM)
    abstract fun onStartWork()
    abstract fun onSetInstrument()

    companion object {
        const val MESSAGE_TOAST_TYPE = 0
        const val MESSAGE_SNACK_TYPE = 1
        const val LOCATION_REQUEST_CODE = 123
    }
}