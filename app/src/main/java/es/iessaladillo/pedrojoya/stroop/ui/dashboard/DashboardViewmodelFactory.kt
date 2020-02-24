package es.iessaladillo.pedrojoya.stroop.ui.dashboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.UserDao

class DashboardViewmodelFactory (
    private val userDao: UserDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DashboardViewModel(userDao, application) as T



}