package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.UserGameDao

class RankingViewModelFactory(
    private val userGameDao: UserGameDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        RankingViewModel(userGameDao, application) as T


}