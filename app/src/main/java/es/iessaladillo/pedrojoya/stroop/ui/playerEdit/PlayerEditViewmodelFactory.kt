package es.iessaladillo.pedrojoya.stroop.ui.playerEdit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.UserDao
import es.iessaladillo.pedrojoya.stroop.ui.playerAdd.PlayerEditViewmodel

class PlayerEditViewmodelFactory (
    private val userDao: UserDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PlayerEditViewmodel(userDao,application) as T


}