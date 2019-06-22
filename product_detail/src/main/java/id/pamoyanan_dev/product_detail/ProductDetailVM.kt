package id.pamoyanan_dev.product_detail

import id.pamoyanan_dev.l_extras.base.BaseViewModel
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductDetailVM(mainApp: MainApp) : BaseViewModel(mainApp) {

    fun insertBuyedMovie(movieFilter: MovieFilter) {
        scope.launch {
            eventShowProgress.postValue(true)
            repository.insertMovieFilter(movieFilter)
            delay(1000)
            if (repository.getAllMoviesFilter().isNotEmpty()) {
                eventGlobalMessage.postValue("Payment is Success")
                delay(1000)
                eventShowProgress.postValue(false)
            } else {
                eventGlobalMessage.postValue("Payment is Failed")
                delay(1000)
                eventShowProgress.postValue(false)
            }
        }
    }

}