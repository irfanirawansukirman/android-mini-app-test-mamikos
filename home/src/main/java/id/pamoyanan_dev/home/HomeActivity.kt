package id.pamoyanan_dev.home

import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import id.pamoyanan_dev.home.databinding.HomeActivityBinding
import id.pamoyanan_dev.home.master.MasterFragment
import id.pamoyanan_dev.home.news.NewsFragment
import id.pamoyanan_dev.l_extras.base.BaseActivity
import id.pamoyanan_dev.l_extras.ext.replaceFragmentInActivity
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : BaseActivity<HomeActivityBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return when (p0.itemId) {
            R.id.action_home -> {
                replaceFragmentInActivity(MasterFragment.newInstance(), R.id.frame_container)
                true
            }
            R.id.action_news -> {
                replaceFragmentInActivity(NewsFragment.newInstance(), R.id.frame_container)
                true
            }
            R.id.action_transaction -> {
                replaceFragmentInActivity(MasterFragment.newInstance(), R.id.frame_container)
                true
            }
            else -> {
                replaceFragmentInActivity(NewsFragment.newInstance(), R.id.frame_container)
                true
            }
        }
    }

    override fun bindLayoutRes() = R.layout.home_activity

    override fun bindToolbarId() = R.id.toolbar

    override fun bindRootFragment() = MasterFragment.newInstance()

    override fun bindFragmentContainerId() = R.id.frame_container

    override fun onStartWork() {
        bottom_home.setOnNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_home_menu, menu)
        return true
    }

}
