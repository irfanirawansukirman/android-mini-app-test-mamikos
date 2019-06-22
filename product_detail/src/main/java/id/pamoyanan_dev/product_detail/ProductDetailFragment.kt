package id.pamoyanan_dev.product_detail

import android.arch.lifecycle.Observer
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import com.google.gson.Gson
import id.pamoyanan_dev.l_extras.base.BaseFragment
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.ext.getViewModel
import id.pamoyanan_dev.l_extras.ext.putArgs
import id.pamoyanan_dev.movieshop.AppConst.MOVIE_ITEM
import id.pamoyanan_dev.movieshop.MainApp
import id.pamoyanan_dev.product_detail.databinding.ProductDetailFragmentBinding
import kotlinx.android.synthetic.main.product_detail_fragment.*
import id.pamoyanan_dev.l_extras.R as extrasR

class ProductDetailFragment : BaseFragment<ProductDetailFragmentBinding, ProductDetailVM>() {

    private lateinit var movieAsObject: MovieFilter
    private lateinit var productDetailVM: ProductDetailVM

    override fun bindLayoutRes() = R.layout.product_detail_fragment

    override fun onSetViewModel(): ProductDetailVM {
        return getViewModel { ProductDetailVM(MainApp.instance) }
    }

    override fun onLoadObserver(baseViewModel: ProductDetailVM) {
        productDetailVM = baseViewModel.apply {
            eventShowProgress.observe(this@ProductDetailFragment, Observer { state ->
                state?.let {
                    if (!it) requireActivity().finish()
                }
            })
        }
    }

    override fun onStartWork() {
        try {
            val movieAsString = requireActivity().intent.getStringExtra(MOVIE_ITEM)
            movieAsObject = Gson().fromJson<MovieFilter>(movieAsString, MovieFilter::class.java)
            viewBinding.apply {
                rating = movieAsObject.vote
                description = movieAsObject.description
                imageUrl = movieAsObject.imageUrl
            }
        } catch (e: Exception) {
        }

        setupViewListener()
    }

    override fun onSetInstrument() {

    }

    private fun setupViewListener() {
        scrollview_productdetail.viewTreeObserver.addOnScrollChangedListener {
            val scrollBounds = Rect()
            scrollview_productdetail.getHitRect(scrollBounds)

            val productDetailActivity = (requireActivity() as ProductDetailActivity)
            val cardContainerProductDetail = productDetailActivity.getCardContainerProductDetail()
            if (frame_productDetail_imageBackdrop.getLocalVisibleRect(scrollBounds)) {
                cardContainerProductDetail.apply {
                    setCardBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
                    cardElevation = CARD_EVEVATION_DEFAULT
                }
            } else {
                cardContainerProductDetail.apply {
                    setCardBackgroundColor(ContextCompat.getColor(requireContext(), extrasR.color.colorPrimaryDark))
                    cardElevation = CARD_ELEVATION
                }
            }
        }
        btn_productDetail_submit.setOnClickListener {
            val buyMovieObj = MovieFilter(
                    id = null,
                    title = movieAsObject.title,
                    description = movieAsObject.description,
                    vote = movieAsObject.vote,
                    imageUrl = movieAsObject.imageUrl,
                    type = movieAsObject.type,
                    price = "Rp. 100.000"
            )

            productDetailVM.insertBuyedMovie(buyMovieObj)
        }
    }

    companion object {
        const val CARD_ELEVATION = 4f
        const val CARD_EVEVATION_DEFAULT = 0f

        fun newInstance() = ProductDetailFragment().putArgs { }
    }

}