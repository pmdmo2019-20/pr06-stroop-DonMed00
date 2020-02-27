package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.data.UserGameDao
import es.iessaladillo.pedrojoya.stroop.data.pojo.UserAndGame

class RankingViewModel(
    private val userGameDao: UserGameDao,
    private val application: Application
) : ViewModel() {

    val userGames: LiveData<List<UserAndGame>> = queryAllUserGames()
    val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(application)
    }


    fun queryAllUserGames(): LiveData<List<UserAndGame>> {
        return userGameDao.queryAllUserGame()
    }

    fun queryAllUserGamesTime(): LiveData<List<UserAndGame>> {
        return userGameDao.queryAllUserGameTime()
    }

    fun queryAllUserGamesAttempts(): LiveData<List<UserAndGame>> {
        return userGameDao.queryAllUserGameAttempts()
    }


}