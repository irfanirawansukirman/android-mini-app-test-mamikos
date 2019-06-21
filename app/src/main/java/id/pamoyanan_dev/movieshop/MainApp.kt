package id.pamoyanan_dev.movieshop

import android.app.Application
import android.content.Context
import com.facebook.FacebookSdk

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(this)

        instance = this
    }

    companion object {
        lateinit var instance: MainApp

        fun getAppContext(): Context = instance.applicationContext
    }

}