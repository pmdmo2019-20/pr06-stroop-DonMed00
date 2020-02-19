package es.iessaladillo.pedrojoya.stroop.ui.player

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.data.UserDao
import es.iessaladillo.pedrojoya.stroop.data.entity.User

class PlayerViewmodel (
    private val userDao: UserDao,
    private val application: Application
) : ViewModel() {



    fun queryAllUsers(): LiveData<List<User>> {
       return userDao.queryAllUsers()
    }
    fun queryCount(): LiveData<Int> {
        return userDao.queryCount()
    }

    fun queryUser(userId: Long): LiveData<User> {
        return userDao.queryUser(userId)
    }

}