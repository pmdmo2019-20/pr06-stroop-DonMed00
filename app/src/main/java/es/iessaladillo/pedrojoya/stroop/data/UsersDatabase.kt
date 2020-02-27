package es.iessaladillo.pedrojoya.stroop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.entity.Game
import es.iessaladillo.pedrojoya.stroop.data.entity.User
import es.iessaladillo.pedrojoya.stroop.data.entity.UserGame
import kotlin.concurrent.thread

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
                        ).addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                thread {
                                    INSTANCE!!.userDao.insertUser(
                                        User(
                                            0,
                                            "Adrian",
                                            R.drawable.avatar_01_mexican
                                        )
                                    )
                                    INSTANCE!!.gameDao.insertGame(
                                        Game(0, "Time", 1, 20, 5, 50)
                                    )
                                    INSTANCE!!.gameDao.insertGame(
                                        Game(0, "Attemtps", 2, 56, 20, 200)
                                    )

                                    INSTANCE!!.userGameDao.insertUserGame(UserGame(1, 1))
                                    INSTANCE!!.userGameDao.insertUserGame(UserGame(1, 2))
                                }

                            }
                        })

                            .allowMainThreadQueries()
                            .build()


                    }
                }
            }
            return INSTANCE!!

        }
    }
}
