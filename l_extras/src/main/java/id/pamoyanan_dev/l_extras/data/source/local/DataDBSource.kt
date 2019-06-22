package id.pamoyanan_dev.l_extras.data.source.local

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import id.pamoyanan_dev.l_extras.data.model.JadwalSholat
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.data.source.local.dao.MovieShopDao

@Database(entities = [MovieFilter::class], version = 1, exportSchema = false)
abstract class DataDBSource : RoomDatabase() {

    abstract fun movieShopDao(): MovieShopDao

    companion object {
        @Volatile
        private var INSTANCE: DataDBSource? = null

        fun getInstance(context: Context): DataDBSource =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context,
                        DataDBSource::class.java, "movies.db"
                )
                        .fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION_1_2)
                        .build()

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE tb_movie_filter ADD COLUMN last_update INTEGER")
            }
        }
    }

}