package id.pamoyanan_dev.movieshop

object AppNavigator {

    // app package
    const val BASE_PACKAGE = "id.pamoyanan_dev."

    // feature package
    const val AUTH_PATH = "auth.AuthActivity"
    const val HOME_PATH = "home.HomeActivity"
    const val PRODUCT_DETAIL_PATH = "product_detail.ProductDetailActivity"

    fun getAuthRoute() = BASE_PACKAGE + AUTH_PATH
    fun getHomeRoute() = BASE_PACKAGE + HOME_PATH
    fun getProductDetailRoute() = BASE_PACKAGE + PRODUCT_DETAIL_PATH

}