package id.pamoyanan_dev.home

import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import id.pamoyanan_dev.home.databinding.HomeActivityBinding
import id.pamoyanan_dev.home.master.MasterFragment
import id.pamoyanan_dev.home.news.NewsFragment
import id.pamoyanan_dev.l_extras.base.BaseActivity
import id.pamoyanan_dev.l_extras.ext.gone
import id.pamoyanan_dev.l_extras.ext.replaceFragmentInActivity
import id.pamoyanan_dev.l_extras.ext.visible
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : BaseActivity<HomeActivityBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var actionToolbarMenu: Menu? = null

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return when (p0.itemId) {
            R.id.action_home -> {
                replaceFragmentInActivity(MasterFragment.newInstance(), R.id.frame_container)
                setupLikeButtonVisibility(true)
                setupSearchContainerVisibility(true)
                setupToolbarTitleVisibility(false)
                setupActionToolbarMenuVisibility(true)
                true
            }
            R.id.action_news -> {
                replaceFragmentInActivity(NewsFragment.newInstance(), R.id.frame_container)
                setupLikeButtonVisibility(false)
                setupSearchContainerVisibility(false)
                setupToolbarTitleVisibility(true)
                setupActionToolbarMenuVisibility(false)
                true
            }
            R.id.action_transaction -> {
                // replaceFragmentInActivity(MasterFragment.newInstance(), R.id.frame_container)
                true
            }
            else -> {
                // replaceFragmentInActivity(NewsFragment.newInstance(), R.id.frame_container)
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

        txt_toolbar_title.text = "Entertainment News"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_home_menu, menu)
        actionToolbarMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when {
        item?.itemId == android.R.id.home -> {
            true
        }
        else -> false
    }

    private fun setupSearchContainerVisibility(isVisible: Boolean) {
        if (isVisible) {
            lin_toolbar_searchContainer.visible()
        } else {
            lin_toolbar_searchContainer.gone()
        }
    }

    private fun setupLikeButtonVisibility(isVisible: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isVisible)
    }

    private fun setupToolbarTitleVisibility(isVisible: Boolean) {
        if (isVisible) {
            txt_toolbar_title.visible()
        } else {
            txt_toolbar_title.gone()
        }
    }

    private fun setupActionToolbarMenuVisibility(isVisible: Boolean) {
        actionToolbarMenu?.findItem(R.id.action_bookmark)?.isVisible = isVisible
    }

}
