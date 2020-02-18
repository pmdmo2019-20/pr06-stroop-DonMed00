package es.iessaladillo.pedrojoya.stroop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.iessaladillo.pedrojoya.stroop.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    ​
    abstract val userDao: UserDao
    ​
    companion object {
        ​
        @Volatile
        private var INSTANCE: AppDatabase? = null
        ​
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "app_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
        ​
    }
    ​
}