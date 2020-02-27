package es.iessaladillo.pedrojoya.stroop.data

import androidx.lifecycle.LiveData
import androidx.room.*
import es.iessaladillo.pedrojoya.stroop.data.entity.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM Game")
    fun queryAllGames(): LiveData<List<Game>>





    @Insert
    fun insertGame(game: Game)
}

