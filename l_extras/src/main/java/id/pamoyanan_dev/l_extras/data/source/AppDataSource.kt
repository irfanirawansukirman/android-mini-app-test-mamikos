package id.pamoyanan_dev.l_extras.data.source

import id.pamoyanan_dev.l_extras.data.model.JadwalSholat
import id.pamoyanan_dev.l_extras.data.model.Result

interface AppDataSource {

    suspend fun getAllMovies(): List<Result>?

    suspend fun insetAllJadwalSholat(data: List<JadwalSholat>)

    suspend fun getAllJadwalSholat(): List<JadwalSholat>

}