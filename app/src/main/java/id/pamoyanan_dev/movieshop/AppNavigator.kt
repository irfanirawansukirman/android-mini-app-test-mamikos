package id.pamoyanan_dev.movieshop

object AppNavigator {

    // app package
    const val BASE_PACKAGE = "id.pamoyanan_dev."

    // feature package
    const val AUTH_PATH = "auth.AuthActivity"
    const val HOME_PATH = "home.HomeActivity"

    fun getAuthRoute() = BASE_PACKAGE + AUTH_PATH
    fun getHomeRoute() = BASE_PACKAGE + HOME_PATH

}