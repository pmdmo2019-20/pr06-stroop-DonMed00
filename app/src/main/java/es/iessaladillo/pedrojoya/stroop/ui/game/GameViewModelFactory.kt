package es.iessaladillo.pedrojoya.stroop.ui.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.GameDao
import es.iessaladillo.pedrojoya.stroop.data.UserGameDao

class GameViewModelFactory(
    private val gameDao: GameDao,
    private val userGameDao: UserGameDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        GameViewModel(gameDao,userGameDao,application) as T


}