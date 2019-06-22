package id.pamoyanan_dev.l_extras.data.source.local

import android.content.Context
import id.pamoyanan_dev.l_extras.data.model.Article
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.data.model.Result
import id.pamoyanan_dev.l_extras.data.source.AppDataSource
import id.pamoyanan_dev.l_extras.data.source.local.dao.MovieShopDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val context: Context) : AppDataSource {

    override suspend fun getAllMovies(): List<Result>? {
        return emptyList()
    }

    private val movieShopDao: MovieShopDao by lazy {
        DataDBSource.getInstance(context).movieShopDao()
    }

    override suspend fun insertMovieFilter(movieFilter: MovieFilter) {
        withContext(Dispatchers.IO) {
            movieShopDao.insertMovieFilter(movieFilter)
        }
    }

    override suspend fun getAllMoviesFilter(): List<MovieFilter> {
        return withContext(Dispatchers.IO) {
            movieShopDao.getAllMoviesFilter()
        }
    }

    override suspend fun getAllEntertainmentNews(): List<Article>? {
        throw Exception("You can't get entertainment news list from local database")
    }

}