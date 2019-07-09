package id.pamoyanan_dev.dashboard.productslist

import id.pamoyanan_dev.l_extras.base.BaseViewModel
import id.pamoyanan_dev.l_extras.data.model.ContentProducts
import id.pamoyanan_dev.l_extras.util.SingleLiveEvent
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsListVM(mainApp: MainApp) : BaseViewModel(mainApp) {

    val productsListEvent = SingleLiveEvent<List<ContentProducts>>()

    fun getProductsList(productType: String) {
        scope.launch {
            eventShowProgress.postValue(true)
            val productsListResponse = repository.getAllProductsList(productType)

            withContext(Dispatchers.Main) {
                eventShowProgress.value = false
                productsListEvent.value = productsListResponse
            }
        }
    }
}