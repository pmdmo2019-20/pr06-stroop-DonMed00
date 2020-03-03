package es.iessaladillo.pedrojoya.stroop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.iessaladillo.pedrojoya.stroop.data.entity.Game
import es.iessaladillo.pedrojoya.stroop.data.entity.User
import es.iessaladillo.pedrojoya.stroop.data.entity.UserGame

@Database(
    entities = [User::class, Game::class, UserGame::class],
    version = 1,
    exportSchema = true
)
abstract class UsersDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val gameDao: GameDao
    abstract val userGameDao: UserGameDao


    companion object {

        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            UsersDatabase::class.java,
                            "users_database"
                        )
                            .allowMainThreadQueries()
                            .build()


                    }
                }
            }
            return INSTANCE!!

        }
    }
}
