package es.iessaladillo.pedrojoya.stroop.ui.player

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.data.UserDao
import es.iessaladillo.pedrojoya.stroop.data.entity.User

class PlayerViewmodel (
    private val userDao: UserDao,
    private val application: Application
) : ViewModel() {

    val users: LiveData<List<User>> = queryAllUsers()

    private val _currenUser : MutableLiveData<Long> = MutableLiveData(1)
    val currentUser : LiveData<Long>
        get()=_currenUser

    fun queryAllUsers(): LiveData<List<User>> {
        return userDao.queryAllUsers()
    }

    fun queryUser(userId: Long): LiveData<User> {
        return userDao.queryUser(userId)
    }

}