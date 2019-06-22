package id.pamoyanan_dev.l_extras.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Movies(
        val page: Int?,
        val results: List<Result>?,
        val total_pages: Int?,
        val total_results: Int?
)

@Serializable
data class Result(
        val adult: Boolean?,
        val backdrop_path: String?,
        val genre_ids: List<Int?>?,
        val id: Int?,
        val original_language: String?,
        val original_title: String?,
        val overview: String?,
        val popularity: Double?,
        val poster_path: String?,
        val release_date: String?,
        val title: String?,
        val video: Boolean?,
        val vote_average: Double?,
        val vote_count: Int?
)

@Serializable
@Entity(tableName = "tb_movie_filter")
data class MovieFilter(
        @PrimaryKey(autoGenerate = true)
        val id: Int?,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "description")
        val description: String,
        @ColumnInfo(name = "vote")
        val vote: String,
        @ColumnInfo(name = "imageUrl")
        val imageUrl: String,
        @ColumnInfo(name = "type")
        val type: Int,
        @ColumnInfo(name = "price")
        val price: String
)