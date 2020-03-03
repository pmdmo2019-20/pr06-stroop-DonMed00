package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.data.UserGameDao
import es.iessaladillo.pedrojoya.stroop.data.pojo.UserAndGame

class RankingViewModel(
    private val userGameDao: UserGameDao,
    private val application: Application
) : ViewModel() {


    val _userGames: MutableLiveData<List<UserAndGame>> = MutableLiveData()
    val userGames: LiveData<List<UserAndGame>>
    get() = _userGames



    val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(application)
    }

    init {
        queryAllUserGames()
    }



    fun queryAllUserGames() {
        _userGames.value= userGameDao.queryAllUserGame()
    }

    fun queryAllUserGamesTime() {
        _userGames.value= userGameDao.queryAllUserGameTime()
    }

    fun queryAllUserGamesAttempts() {
        _userGames.value= userGameDao.queryAllUserGameAttempts()
    }




}