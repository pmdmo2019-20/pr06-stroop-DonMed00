package es.iessaladillo.pedrojoya.stroop.ui.player

import android.app.Application
import android.content.SharedPreferences
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
    val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(application)
    }

    private val _currentUserId : MutableLiveData<Long> = MutableLiveData()
    val currentUserId : LiveData<Long>
        get()=_currentUserId

    private val _currentUser : MutableLiveData<User> = MutableLiveData()

    init {
        _currentUserId.value=settings.getLong("currentPlayer",-1)
    }

    private fun queryAllUsers(): LiveData<List<User>> {
        return userDao.queryAllUsers()
    }

    fun queryUser(userId: Long) =userDao.queryUser(userId)


    fun setCurrentUserId(userId: Long){
        _currentUserId.value=userId
    }

}