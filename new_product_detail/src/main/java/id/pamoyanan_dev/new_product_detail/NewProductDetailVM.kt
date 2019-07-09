package id.pamoyanan_dev.new_product_detail

import id.pamoyanan_dev.l_extras.base.BaseViewModel
import id.pamoyanan_dev.l_extras.util.SingleLiveEvent
import id.pamoyanan_dev.movieshop.MainApp

class NewProductDetailVM(mainApp: MainApp) : BaseViewModel(mainApp) {

    val isRelatedProductNotEmpty = SingleLiveEvent<Boolean>()

}