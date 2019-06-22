package id.pamoyanan_dev.product_detail

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import id.pamoyanan_dev.l_extras.base.BaseActivity
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.movieshop.AppConst.MOVIE_ITEM
import id.pamoyanan_dev.product_detail.databinding.ProductDetailActivityBinding
import kotlinx.android.synthetic.main.product_detail_activity.*
import id.pamoyanan_dev.l_extras.R as extrasR


class ProductDetailActivity : BaseActivity<ProductDetailActivityBinding>() {

    private lateinit var movieAsObject: MovieFilter

    override fun bindLayoutRes() = R.layout.product_detail_activity

    override fun bindToolbarId() = R.id.toolbar

    override fun bindRootFragment() = ProductDetailFragment.newInstance()

    override fun bindFragmentContainerId() = R.id.frame_container

    override fun onStartWork() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        try {
            val movieAsString = intent.getStringExtra(MOVIE_ITEM)
            movieAsObject = Gson().fromJson<MovieFilter>(movieAsString, MovieFilter::class.java)
            txt_toolbar_title.text = movieAsObject.title
        } catch (e: Exception) {
            // do nothing
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_detail_menu, menu)
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
            putExtra(Intent.EXTRA_TEXT, movieAsObject.title)
            type = "text/plain"
        }
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(sendIntent, "Share"))
        } else {
            // no app can handle this intent, do something else
        }
    }

    fun getCardContainerProductDetail() = card_productDetail_toolbarContainer

}
