package id.pamoyanan_dev.l_extras.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Products(
    val list: List<ContentProducts>?,
    val status: Boolean
)

@Serializable
data class ContentProducts(
    val brand: String,
    val description: String,
    val id: Int,
    val image_url: String,
    val name: String,
    val price: String
)