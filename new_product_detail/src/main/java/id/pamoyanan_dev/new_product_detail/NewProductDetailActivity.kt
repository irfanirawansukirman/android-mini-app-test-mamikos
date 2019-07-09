package id.pamoyanan_dev.new_product_detail

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import id.pamoyanan_dev.l_extras.base.BaseActivity
import id.pamoyanan_dev.new_product_detail.databinding.NewProductDetailActivityBinding

class NewProductDetailActivity : BaseActivity<NewProductDetailActivityBinding>() {

    override fun bindLayoutRes() = R.layout.new_product_detail_activity

    override fun bindToolbarId() = R.id.toolbar

    override fun bindRootFragment() = NewProductDetailFragment.newInstance()

    override fun bindFragmentContainerId() = R.id.frame_container

    override fun onStartWork() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_product_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when {
        item?.itemId == R.id.action_share -> {
            shareContentToTheWorld()
            true
        }
        item?.itemId == android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

    private fun shareContentToTheWorld() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Holla")
            type = "text/plain"
        }
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(sendIntent, "Share"))
        } else {
            // no app can handle this intent, do something else
        }
    }

}
