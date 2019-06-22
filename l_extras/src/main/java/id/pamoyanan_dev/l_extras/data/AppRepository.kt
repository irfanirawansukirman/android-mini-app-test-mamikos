package id.pamoyanan_dev.l_extras.data

import id.pamoyanan_dev.l_extras.data.model.Article
import id.pamoyanan_dev.l_extras.data.model.JadwalSholat
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.data.model.Result
import id.pamoyanan_dev.l_extras.data.source.AppDataSource
import id.pamoyanan_dev.l_extras.data.source.local.LocalDataSource
import id.pamoyanan_dev.l_extras.data.source.remote.RemoteDataSource

class AppRepository(
    private val remoteDataSource: AppDataSource,
    private val localDataSource: AppDataSource
) : AppDataSource {

    override suspend fun getAllEntertainmentNews(): List<Article>? {
        return remoteDataSource.getAllEntertainmentNews()
    }

    override suspend fun getAllMovies(): List<Result>? {
        return remoteDataSource.getAllMovies()
    }

    override suspend fun insertMovieFilter(movieFilter: MovieFilter) {
        localDataSource.insertMovieFilter(movieFilter)
    }

    override suspend fun getAllMoviesFilter(): List<MovieFilter> {
        return localDataSource.getAllMoviesFilter()
    }

    companion object {
        var mRepository: AppRepository? = null

        @JvmStatic
        fun getInstance(dataRemoteSource: RemoteDataSource, dataLocalSource: LocalDataSource): AppRepository {
            if (mRepository == null) {
                mRepository = AppRepository(remoteDataSource = dataRemoteSource, localDataSource = dataLocalSource)
            }
            return mRepository!!
        }
    }
}