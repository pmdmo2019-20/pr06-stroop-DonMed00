package es.iessaladillo.pedrojoya.stroop.ui.playerAdd

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.UserDao

class PlayerAddViewmodelFactory (
    private val userDao: UserDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PlayerAddViewmodel(userDao,application) as T


}