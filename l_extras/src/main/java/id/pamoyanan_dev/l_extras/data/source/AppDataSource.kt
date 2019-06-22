package id.pamoyanan_dev.l_extras.data.source

import id.pamoyanan_dev.l_extras.data.model.Article
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.data.model.Result

interface AppDataSource {

    suspend fun searchMovie(query: String): List<Result>?

    suspend fun getAllMovies(): List<Result>?

    suspend fun getAllEntertainmentNews(): List<Article>?

    suspend fun insertMovieFilter(movieFilter: MovieFilter)

    suspend fun getAllMoviesFilter(): List<MovieFilter>

}