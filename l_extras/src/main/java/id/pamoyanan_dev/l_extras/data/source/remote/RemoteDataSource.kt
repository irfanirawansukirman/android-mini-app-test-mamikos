package id.pamoyanan_dev.l_extras.data.source.remote

import android.app.Application
import android.util.Log
import id.pamoyanan_dev.l_extras.data.model.Article
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.data.model.Result
import id.pamoyanan_dev.l_extras.data.source.AppDataSource
import id.pamoyanan_dev.l_extras.util.Results
import kotlinx.io.IOException
import retrofit2.Response

class RemoteDataSource(private val application: Application) : AppDataSource {

    private val entertaimentNewsApiService: ApiService by lazy {
        ApiService.newBuilder(application, BASE_URL_NEWS)
    }

    private val movieApiService: ApiService by lazy {
        ApiService.newBuilder(application, BASE_URL_MOVIE)
    }

    override suspend fun getAllMovies(): List<Result>? {
        val movieResponse = safeApiCall(
                call = { movieApiService.getMoviesAsync().await() },
                errorMessage = "Error Fetching Movies List"
        )

        return movieResponse?.results
    }

    override suspend fun insertMovieFilter(movieFilter: MovieFilter) {
        throw Exception("Can't insert data from remote source")
    }

    override suspend fun getAllMoviesFilter(): List<MovieFilter> {
        return emptyList()
    }

    override suspend fun getAllEntertainmentNews(): List<Article>? {
        val entertainmentNewsResponse = safeApiCall(
                call = { entertaimentNewsApiService.getEntertaimentNewsAsync().await() },
                errorMessage = "Error Fetching Entertainment News"
        )

        return entertainmentNewsResponse?.articles
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result: Results<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is Results.Success ->
                data = result.data

            is Results.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>, errorMessage: String): Results<T> {
        val response = call.invoke()
        if (response.isSuccessful) return Results.Success(response.body()!!)

        return Results.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }

    companion object {
        const val BASE_URL_MOVIE = "https://api.themoviedb.org/"
        const val BASE_URL_NEWS = "https://newsapi.org/"
    }

}