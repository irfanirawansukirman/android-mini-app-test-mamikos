package id.pamoyanan_dev.l_extras.data.source.local

import android.content.Context
import id.pamoyanan_dev.l_extras.data.model.JadwalSholat
import id.pamoyanan_dev.l_extras.data.model.Result
import id.pamoyanan_dev.l_extras.data.source.AppDataSource
import id.pamoyanan_dev.l_extras.data.source.local.dao.JadwalSholatDao
import id.pamoyanan_dev.l_extras.data.source.local.dao.MovieShopDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LocalDataSource(private val context: Context) : AppDataSource {

    private val jadwalSholatDao: JadwalSholatDao by lazy {
        DataDBSource.getInstance(context).jadwalSholatDao()
    }

    private val movieShopDao: MovieShopDao by lazy {
        DataDBSource.getInstance(context).movieShopDao()
    }

    override suspend fun getAllJadwalSholat(): List<JadwalSholat> {
        return withContext(Dispatchers.IO) {
            jadwalSholatDao.getAllJadwalSholat()
        }
    }

    override suspend fun insetAllJadwalSholat(data: List<JadwalSholat>) {
        withContext(Dispatchers.IO) {
            jadwalSholatDao.apply {
                deleteAllAdzan()
                delay(250)
                insertAllJadwalSholat(data)
            }
        }
    }

    override suspend fun getAllMovies(): List<Result>? {
        throw Exception("You can't get movies list from local database")
    }

}