package id.pamoyanan_dev.dashboard.productslist

import android.arch.lifecycle.Observer
import android.support.v7.widget.GridLayoutManager
import id.pamoyanan_dev.dashboard.R
import id.pamoyanan_dev.dashboard.databinding.ProductsListFragmentBinding
import id.pamoyanan_dev.l_extras.base.BaseFragment
import id.pamoyanan_dev.l_extras.data.model.ContentProducts
import id.pamoyanan_dev.l_extras.ext.navigatorImplicit
import id.pamoyanan_dev.l_extras.ext.putArgs
import id.pamoyanan_dev.movieshop.AppNavigator.getNewProductDetailRoute
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.android.synthetic.main.products_list_fragment.*

class ProductsListFragment : BaseFragment<ProductsListFragmentBinding, ProductsListVM>(),
    ProductsListItemListener {

    private lateinit var productsListVM: ProductsListVM

    override fun onProductClicked(pos: Int, contentAsString: String) {
        requireContext().navigatorImplicit(getNewProductDetailRoute()) {
            putExtra(PRODUCT_POS, pos)
            putExtra(PRODUCT_AS_STRING, contentAsString)
        }
    }

    override fun bindLayoutRes() = R.layout.products_list_fragment

    override fun onSetViewModel(): ProductsListVM {
        return ProductsListVM(MainApp.instance)
    }

    override fun onLoadObserver(baseViewModel: ProductsListVM) {
        productsListVM = baseViewModel.apply {
            productsListEvent.observe(this@ProductsListFragment, Observer { data ->
                data?.let {
                    setupProductsList(productsList = it)
                }
            })
        }
    }

    override fun onStartWork() {
        val productType = arguments?.getString(PRODUCT_TYPE_SELECTED) ?: ""
        productsListVM.getProductsList(productType = productType)
    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                productsListVM = it
            }
        }
    }

    private fun setupProductsList(productsList: List<ContentProducts>) {
        rec_productslist.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter =
                ProductsListAdapter(productsList = productsList, productsListItemListener = this@ProductsListFragment)
        }
    }

    companion object {
        private const val PRODUCT_TYPE_SELECTED = "PRODUCT_TYPE_SELECTED"
        private const val PRODUCT_POS = "PRODUCT_POS"
        private const val PRODUCT_AS_STRING = "PRODUCT_AS_STRING"

        fun newInstance(productType: String) = ProductsListFragment().putArgs {
            putString(PRODUCT_TYPE_SELECTED, productType)
        }
    }
}