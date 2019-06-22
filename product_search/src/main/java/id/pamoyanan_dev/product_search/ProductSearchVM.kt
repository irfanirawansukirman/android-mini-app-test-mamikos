package id.pamoyanan_dev.product_search

import id.pamoyanan_dev.l_extras.base.BaseViewModel
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.util.SingleLiveEvent
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductSearchVM(mainApp: MainApp) : BaseViewModel(mainApp) {

    val movieFilterEvent = SingleLiveEvent<List<MovieFilter>>()

    fun searchMovie(query: String) = scope.launch {
        eventShowProgress.postValue(true)
        val movieResponse = repository.searchMovie(query)

        withContext(Dispatchers.Main) {
            val moviesListFinal = mutableListOf<MovieFilter>()
            moviesListFinal.apply {
                clear()
                movieResponse?.forEachIndexed { _, item ->
                    add(
                            MovieFilter(
                                    id = null,
                                    title = item.original_title ?: "",
                                    description = item.overview ?: "",
                                    vote = "${item.vote_average.toString()}/10",
                                    imageUrl = "https://image.tmdb.org/t/p/w500${item.poster_path}",
                                    type = 1,
                                    price = ""
                            )
                    )
                }
            }
            eventShowProgress.value = false
            movieFilterEvent.value = moviesListFinal
        }
    }

}