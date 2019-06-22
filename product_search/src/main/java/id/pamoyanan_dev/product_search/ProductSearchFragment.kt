package id.pamoyanan_dev.product_search

import android.arch.lifecycle.Observer
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import id.pamoyanan_dev.l_extras.base.BaseFragment
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.ext.getViewModel
import id.pamoyanan_dev.l_extras.ext.putArgs
import id.pamoyanan_dev.l_extras.ext.verticalListStyle
import id.pamoyanan_dev.l_extras.ext.visible
import id.pamoyanan_dev.movieshop.MainApp
import id.pamoyanan_dev.product_search.databinding.ProductSearchFragmentBinding
import kotlinx.android.synthetic.main.product_search_fragment.*

class ProductSearchFragment : BaseFragment<ProductSearchFragmentBinding, ProductSearchVM>() {

    private lateinit var productSearchVM: ProductSearchVM

    override fun bindLayoutRes() = R.layout.product_search_fragment

    override fun onSetViewModel(): ProductSearchVM {
        return getViewModel { ProductSearchVM(MainApp.instance) }
    }

    override fun onLoadObserver(baseViewModel: ProductSearchVM) {
        productSearchVM = baseViewModel.apply {
            movieFilterEvent.observe(this@ProductSearchFragment, Observer { data ->
                data?.let {
                    setupMoviesList(it)
                }
            })
        }
    }

    override fun onStartWork() {
        setupToolbar()
        setupViewListener()
    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                productSearchVM = it
            }
        }
    }

    private fun setupMoviesList(data: List<MovieFilter>) {
        rec_productSearch.apply {
            verticalListStyle()
            adapter = ProductSearchAdapter(data)
        }
    }

    private fun setupToolbar() {
        val productSearchActivity = (requireActivity() as ProductSearchActivity)
        productSearchActivity.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)
                setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    private fun setupViewListener() {
        edtxt_productSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Handler().postDelayed({
                    if (s.isNotEmpty()) productSearchVM.searchMovie(s.toString().trim()) else productSearchVM.eventShowProgress.value = false
                }, 500)
            }
        })
    }

    companion object {
        fun newInstance() = ProductSearchFragment().putArgs { }
    }
}