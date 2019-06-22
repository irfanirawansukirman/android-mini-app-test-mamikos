package id.pamoyanan_dev.movieshop

import android.app.Application
import android.content.Context
import com.facebook.FacebookSdk
import com.facebook.stetho.Stetho

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(this)

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        instance = this
    }

    companion object {
        lateinit var instance: MainApp

        fun getAppContext(): Context = instance.applicationContext
    }

}