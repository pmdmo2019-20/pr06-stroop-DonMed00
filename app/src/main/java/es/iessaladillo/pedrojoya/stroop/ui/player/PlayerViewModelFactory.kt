package es.iessaladillo.pedrojoya.stroop.ui.player

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.UserDao
import es.iessaladillo.pedrojoya.stroop.data.UsersDatabase

class PlayerViewModelFactory(
    private val userDao: UserDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PlayerViewmodel(userDao, application) as T


}