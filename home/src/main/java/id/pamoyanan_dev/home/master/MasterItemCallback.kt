package id.pamoyanan_dev.home.master

import id.pamoyanan_dev.l_extras.data.model.MovieFilter

interface MasterItemCallback {
    fun onCategoryMovieSelected(genre: String)
    fun onItemMovieSelected(movieFilter: MovieFilter)
}