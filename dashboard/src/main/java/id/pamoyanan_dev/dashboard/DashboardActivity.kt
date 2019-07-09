package id.pamoyanan_dev.dashboard

import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import id.pamoyanan_dev.dashboard.databinding.DashboardActivityBinding
import id.pamoyanan_dev.dashboard.productslist.ProductsListFragment
import id.pamoyanan_dev.l_extras.base.BaseActivity
import id.pamoyanan_dev.l_extras.ext.replaceFragmentInActivity
import kotlinx.android.synthetic.main.dashboard_activity.*

class DashboardActivity : BaseActivity<DashboardActivityBinding>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_shirt -> {
                replaceFragmentInActivity(ProductsListFragment.newInstance(SHIRT_TYPE), R.id.frame_container)
                true
            }
            R.id.action_jeans -> {
                replaceFragmentInActivity(ProductsListFragment.newInstance(JEANS_TYPE), R.id.frame_container)
                true
            }
            else -> {
                replaceFragmentInActivity(ProductsListFragment.newInstance(HAT_TYPE), R.id.frame_container)
                true
            }
        }
    }

    override fun bindLayoutRes() = R.layout.dashboard_activity

    override fun bindToolbarId() = R.id.toolbar

    override fun bindRootFragment() = ProductsListFragment.newInstance(SHIRT_TYPE)

    override fun bindFragmentContainerId() = R.id.frame_container

    override fun onStartWork() {
        setupViewListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_action_menu, menu)
        return true
    }

    private fun setupViewListener() {
        bottom_dashboard.setOnNavigationItemSelectedListener(this)
    }

    companion object {
        const val SHIRT_TYPE = "SHIRT"
        const val JEANS_TYPE = "JEANS"
        const val HAT_TYPE = "HAT"
    }

}
