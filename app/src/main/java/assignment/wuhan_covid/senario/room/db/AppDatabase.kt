package assignment.wuhan_covid.senario.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import assignment.wuhan_covid.senario.api.model.Data
import assignment.wuhan_covid.senario.room.dao.DataDao

/**
 * Created by GwangMoo You on 2/23/21.
 */
@Database(entities = [Data::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
//                    .addCallback(AppDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}