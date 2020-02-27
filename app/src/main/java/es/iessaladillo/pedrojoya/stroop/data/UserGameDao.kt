package es.iessaladillo.pedrojoya.stroop.data

import androidx.lifecycle.LiveData
import androidx.room.*
import es.iessaladillo.pedrojoya.stroop.data.entity.UserGame
import es.iessaladillo.pedrojoya.stroop.data.pojo.UserAndGame

@Dao
interface UserGameDao {


    @Insert
    fun insertUserGame(userGame: UserGame)

    @Query("SELECT imageId,userName,gameMode,totalTime,totalWords,corrects,points " +
            "FROM UserGame INNER JOIN User on UserGame.userId=User.userId " +
            "INNER JOIN Game on UserGame.gameId=Game.gameId order by points DESC")
    fun queryAllUserGame(): LiveData<List<UserAndGame>>

    @Query("SELECT imageId,userName,gameMode,totalTime,totalWords,corrects,points " +
            "FROM UserGame INNER JOIN User on UserGame.userId=User.userId " +
            "INNER JOIN Game on UserGame.gameId=Game.gameId where gameMode=\"Time\" order by points DESC")
    fun queryAllUserGameTime(): LiveData<List<UserAndGame>>


    @Query("SELECT imageId,userName,gameMode,totalTime,totalWords,corrects,points " +
            "FROM UserGame INNER JOIN User on UserGame.userId=User.userId " +
            "INNER JOIN Game on UserGame.gameId=Game.gameId where gameMode=\"Attempts\" order by points DESC")
    fun queryAllUserGameAttempts(): LiveData<List<UserAndGame>>
}

