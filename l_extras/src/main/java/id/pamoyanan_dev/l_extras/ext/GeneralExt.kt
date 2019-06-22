package id.pamoyanan_dev.l_extras.ext

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

//suspend fun <T> Deferred<BaseApiResponse<T>>.getResult(): ApiResult<T> {
//    return try {
//        val data = this.await()
//        if (data.code == 200 && data.results != null) {
//            ApiResult(data.code, data.message?:"", data.results)
//        } else {
//            ApiResult(data.code, data.message?:"", data.results)
//        }
//
//    } catch (e: Exception) {
//        e.printStackTrace()
//        when (e) {
//            is HttpException -> {
//                val code = e.code()
//                var msg = e.message()
//                val baseDao: BaseApiResponse<T>?
//                try {
//                    val body = e.response().errorBody()
//                    baseDao = Gson().fromJson<BaseApiResponse<T>>(body!!.string(), BaseApiResponse::class.java)
//                } catch (exception: Exception) {
//                    return ApiResult(-1, exception.message?:exception.localizedMessage, null)
//                }
//
//                when (code) {
//                    504 -> {
//                        msg = baseDao?.message ?: "Error Response"
//                    }
//                    502, 404 -> {
//                        msg = baseDao?.message ?: "Error Connect or Resource Not Found"
//                    }
//                    400 -> {
//                        msg = baseDao?.message ?: "Bad Request"
//                    }
//                    401 -> {
//                        msg = baseDao?.message ?: "Not Authorized"
//                    }
//                }
//
//                return ApiResult(baseDao.code, msg, null)
//            }
//            is UnknownHostException -> return ApiResult(-1, "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}", null)
//            is SocketTimeoutException -> return ApiResult(-1, "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}", null)
//            else -> return ApiResult(-1, "Unknown error occured", null)
//        }
//    }
//}

fun String.dateFormatFromTimeString(
    date: String,
    oldFormat: String,
    newFormat: String,
    isLocale: Boolean
): String {
    val dateTimeMillis = if (!TextUtils.isEmpty(date)) {
        SimpleDateFormat(oldFormat, isLocale.isLocaleDate(isLocale)).parse(date).time
    } else {
        0.toLong()
    }

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = dateTimeMillis

    return if (dateTimeMillis != 0.toLong() && dateTimeMillis != null) {
        SimpleDateFormat(newFormat, isLocale.isLocaleDate(isLocale))
            .format(calendar.time)
    } else {
        SimpleDateFormat(newFormat, isLocale.isLocaleDate(isLocale))
            .format(System.currentTimeMillis())
    }
}


fun Boolean.isLocaleDate(
    isLocale: Boolean
): Locale {
    return if (isLocale) Locale("id", "ID")
    else Locale("en", "EN")
}

