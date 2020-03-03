package es.iessaladillo.pedrojoya.stroop.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iessaladillo.pedrojoya.stroop.data.entity.UserGame
import es.iessaladillo.pedrojoya.stroop.data.pojo.UserAndGame

@Dao
interface UserGameDao {


    @Insert
    fun insertUserGame(userGame: UserGame)

    @Query("SELECT imageId,userName,gameMode,totalTime,totalWords,corrects,points " +
            "FROM UserGame INNER JOIN User on UserGame.userId=User.userId " +
            "INNER JOIN Game on UserGame.gameId=Game.gameId order by points DESC,userName LIMIT 5")
    fun queryAllUserGame(): List<UserAndGame>

    @Query("SELECT imageId,userName,gameMode,totalTime,totalWords,corrects,points " +
            "FROM UserGame INNER JOIN User on UserGame.userId=User.userId " +
            "INNER JOIN Game on UserGame.gameId=Game.gameId where gameMode=\"Time\" order by points DESC,userName LIMIT 5")
    fun queryAllUserGameTime(): List<UserAndGame>


    @Query("SELECT imageId,userName,gameMode,totalTime,totalWords,corrects,points " +
            "FROM UserGame INNER JOIN User on UserGame.userId=User.userId " +
            "INNER JOIN Game on UserGame.gameId=Game.gameId where gameMode=\"Attempts\" order by points DESC,userName LIMIT 5")
    fun queryAllUserGameAttempts(): List<UserAndGame>
}

