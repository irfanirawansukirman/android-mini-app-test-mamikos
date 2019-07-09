package id.pamoyanan_dev.movieshop

object AppNavigator {

    // app package
    const val BASE_PACKAGE = "id.pamoyanan_dev."

    // feature package
    const val SPLASH_PATH = "movieshop.SplashActivity"
    const val AUTH_PATH = "auth.AuthActivity"
    const val HOME_PATH = "home.HomeActivity"
    const val PRODUCT_DETAIL_PATH = "product_detail.ProductDetailActivity"
    const val PRODUCT_SEARCH_PATH = "product_search.ProductSearchActivity"

    // new features
    const val DASHBOARD_PATH = "dashboard.DashboardActivity"
    const val NEW_PRODUCT_DETAIL_PATH = "new_product_detail.NewProductDetailActivity"

    fun getAuthRoute() = BASE_PACKAGE + AUTH_PATH
    fun getHomeRoute() = BASE_PACKAGE + HOME_PATH
    fun getProductDetailRoute() = BASE_PACKAGE + PRODUCT_DETAIL_PATH
    fun getSplashRoute() = BASE_PACKAGE + SPLASH_PATH
    fun getProductSearchRoute() = BASE_PACKAGE + PRODUCT_SEARCH_PATH
    fun getDashboardRoute() = BASE_PACKAGE + DASHBOARD_PATH
    fun getNewProductDetailRoute() = BASE_PACKAGE + NEW_PRODUCT_DETAIL_PATH

}