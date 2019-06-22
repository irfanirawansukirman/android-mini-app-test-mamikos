package id.pamoyanan_dev.home.master

import id.pamoyanan_dev.l_extras.base.BaseViewModel
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.util.SingleLiveEvent
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MasterVM(mainApp: MainApp) : BaseViewModel(mainApp) {

    val movieFilterEvent = SingleLiveEvent<List<MovieFilter>>()

    init {
        getMoviesList()
    }

    override fun onCleared() {
        super.onCleared()
        getMoviesList().cancel()
    }

    private fun getMoviesList() = scope.launch {
        eventShowProgress.postValue(true)
        val movieResponse = repository.getAllMovies()

        withContext(Dispatchers.Main) {
            val moviesListFinal = mutableListOf<MovieFilter>()
            moviesListFinal.apply {
                clear()
                movieResponse?.forEachIndexed { i, item ->
                    if (i == 0) {
                        add(MovieFilter(id = null, title = "", description = "", vote = "", imageUrl = "", type = 0, price = ""))
                    } else {
                        add(MovieFilter(
                                id = null,
                                title = item.original_title ?: "",
                                description = item.overview ?: "",
                                vote = "${item.vote_average.toString()}/10",
                                imageUrl = "https://image.tmdb.org/t/p/w500${item.poster_path}",
                                type = 1,
                                price = "")
                        )
                    }
                }
            }
            eventShowProgress.value = false
            movieFilterEvent.value = moviesListFinal
        }
    }
}