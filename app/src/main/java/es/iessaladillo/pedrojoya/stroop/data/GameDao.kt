package es.iessaladillo.pedrojoya.stroop.data

import androidx.lifecycle.LiveData
import androidx.room.*
import es.iessaladillo.pedrojoya.stroop.data.entity.Game
import es.iessaladillo.pedrojoya.stroop.data.entity.User

@Dao
interface GameDao {

    @Query("SELECT * FROM Game")
    fun queryAllGames(): LiveData<List<Game>>





    @Insert
    fun insertGame(game: Game)

    @Query("SELECT * FROM Game WHERE gameId = :gameId")
    fun queryGame(gameId: Int): Game


    @Query("SELECT * FROM Game ORDER BY 1 DESC LIMIT 1")
    fun queryLastGame(): Game
}

