package id.pamoyanan_dev.home.transaction

import id.pamoyanan_dev.l_extras.base.BaseViewModel
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.util.SingleLiveEvent
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.coroutines.launch

class TransactionVM(mainApp: MainApp) : BaseViewModel(mainApp) {

    val checkSizeEvent = SingleLiveEvent<Boolean>()
    var moviesFilterListEvent = SingleLiveEvent<List<MovieFilter>>()

    init {
        scope.launch {
            checkSizeEvent.postValue(repository.getAllMoviesFilter().isNotEmpty())

            val moviesFilter = repository.getAllMoviesFilter()
            if (moviesFilter.isNotEmpty()) moviesFilterListEvent.postValue(moviesFilter)
        }
    }
}