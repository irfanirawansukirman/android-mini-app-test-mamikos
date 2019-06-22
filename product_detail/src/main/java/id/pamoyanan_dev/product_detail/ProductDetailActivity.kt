package id.pamoyanan_dev.product_detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.movieshop.AppConst.MOVIE_ITEM
import kotlinx.android.synthetic.main.product_detail_activity.*

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_activity)
        try {
            val movieAsString = intent.getStringExtra(MOVIE_ITEM)
            val movieAsObj = Gson().fromJson<MovieFilter>(movieAsString, MovieFilter::class.java)
            txt_productDetail_title.text = movieAsObj.title
        } catch (e: Exception) {

        }
    }
}
