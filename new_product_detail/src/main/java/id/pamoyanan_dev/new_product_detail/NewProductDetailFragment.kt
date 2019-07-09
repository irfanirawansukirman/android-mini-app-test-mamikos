package id.pamoyanan_dev.new_product_detail

import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.pamoyanan_dev.l_extras.base.BaseFragment
import id.pamoyanan_dev.l_extras.data.model.ContentProducts
import id.pamoyanan_dev.l_extras.ext.getViewModel
import id.pamoyanan_dev.l_extras.ext.putArgs
import id.pamoyanan_dev.l_extras.util.GlideApp
import id.pamoyanan_dev.movieshop.MainApp
import id.pamoyanan_dev.new_product_detail.databinding.NewProductDetailFragmentBinding
import kotlinx.android.synthetic.main.new_product_detail_activity.*
import kotlinx.android.synthetic.main.new_product_detail_fragment.*
import org.json.JSONArray
import id.pamoyanan_dev.l_extras.R as extrasR


class NewProductDetailFragment : BaseFragment<NewProductDetailFragmentBinding, NewProductDetailVM>() {

    override fun bindLayoutRes() = R.layout.new_product_detail_fragment

    override fun onSetViewModel(): NewProductDetailVM {
        return getViewModel { NewProductDetailVM(MainApp.instance) }
    }

    override fun onLoadObserver(baseViewModel: NewProductDetailVM) {

    }

    override fun onStartWork() {
        setupViewListener()
        setupProductInfo()
    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                newProductDetailVM = it
            }
        }
    }

    private fun setupViewListener() {
        btn_newProductdetail_submit.setOnClickListener { }
    }

    private fun setupProductInfo() {
        val productPos = requireActivity().intent.getIntExtra(PRODUCT_POS, 0)
        val productAsStringArr = requireActivity().intent.getStringExtra(PRODUCT_AS_STRING) ?: ""
        val jsonObj = JSONArray(productAsStringArr)
        val type = object : TypeToken<List<ContentProducts>>() {}.type
        val productAsArr = Gson().fromJson<List<ContentProducts>>(jsonObj.toString(), type)
        val productSelected = productAsArr[productPos]
        txt_newProductDetail_merk.text = productSelected.brand
        txt_newProductDetail_desc.text = productSelected.description
        txt_newProductDetail_total.text = productSelected.price
        GlideApp.with(img_newProductDetail)
            .load(productSelected.image_url)
            .placeholder(extrasR.color.greyBackgroundDefault)
            .error(extrasR.color.greyBackgroundDefault)
            .into(img_newProductDetail)
        (requireActivity() as NewProductDetailActivity).txt_toolbar_title.text = productSelected.name

        val productFilter = productAsArr.filter { item -> item.name != productSelected.name }
        rec_newDetailProduct_relatedProducts.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = NewProductDetailRelatedAdapter(productFilter)
        }

        baseViewModel.isRelatedProductNotEmpty.value = productFilter.isNotEmpty()
    }

    companion object {
        private const val PRODUCT_POS = "PRODUCT_POS"
        private const val PRODUCT_AS_STRING = "PRODUCT_AS_STRING"

        fun newInstance() = NewProductDetailFragment().putArgs { }
    }

}