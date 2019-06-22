package id.pamoyanan_dev.l_extras.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import id.pamoyanan_dev.l_extras.data.model.MovieFilter

@Dao
interface MovieShopDao {

    @Query("SELECT * FROM tb_movie_filter")
    fun getAllMoviesFilter(): List<MovieFilter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieFilter(data: MovieFilter)

}