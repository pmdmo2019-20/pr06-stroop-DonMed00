package es.iessaladillo.pedrojoya.stroop.ui.playerAdd

import android.app.Application
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.data.UserDao
import es.iessaladillo.pedrojoya.stroop.data.entity.User
import kotlin.concurrent.thread

class PlayerAddViewmodel(
    private val userDao: UserDao,
    application: Application
) : ViewModel() {



    fun addUser(userName: String, imageUser: Int){
        thread {
            userDao.insertUser(User(0,userName,imageUser))

        }
    }

}