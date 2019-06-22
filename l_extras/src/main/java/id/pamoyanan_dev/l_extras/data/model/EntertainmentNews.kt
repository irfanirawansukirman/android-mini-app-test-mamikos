package id.pamoyanan_dev.l_extras.data.model

import kotlinx.serialization.Serializable

@Serializable
data class EntertainmentNews(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)

@Serializable
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

@Serializable
data class Source(
    val id: String?,
    val name: String?
)