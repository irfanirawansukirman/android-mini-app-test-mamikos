package id.pamoyanan_dev.movieshop

import android.os.Handler
import android.support.v4.app.Fragment
import id.pamoyanan_dev.l_extras.base.BaseActivity
import id.pamoyanan_dev.l_extras.ext.navigatorImplicit
import id.pamoyanan_dev.movieshop.AppNavigator.getAuthRoute
import id.pamoyanan_dev.movieshop.databinding.SplashActivityBinding

class SplashActivity : BaseActivity<SplashActivityBinding>() {

    override fun bindLayoutRes() = R.layout.splash_activity

    override fun bindToolbarId() = 0

    override fun bindRootFragment(): Fragment? = null

    override fun bindFragmentContainerId(): Int? = null

    override fun onStartWork() {
        Handler().postDelayed({
            navigatorImplicit(getAuthRoute()) {}
            finish()
        }, 1000)
    }

}
