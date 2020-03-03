package es.iessaladillo.pedrojoya.stroop.ui.result


import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.data.UsersDatabase
import es.iessaladillo.pedrojoya.stroop.data.entity.UserGame
import kotlinx.android.synthetic.main.result_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment(R.layout.result_fragment) {



    val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {

        setupToolbar()
        setupLbls()
        saveUserGame()

    }

    private fun saveUserGame() {
        val game = UsersDatabase.getInstance(this.requireContext()).gameDao.queryLastGame()

        val user = UsersDatabase.getInstance(this.requireContext()).userDao.queryUser(settings.getLong("currentPlayer",-1))

        UsersDatabase.getInstance(this.requireContext()).userGameDao.insertUserGame(UserGame(user.userId,game.gameId))
    }

    private fun setupLbls() {
        val game = UsersDatabase.getInstance(this.requireContext()).gameDao.queryLastGame()

        val user = UsersDatabase.getInstance(this.requireContext()).userDao.queryUser(settings.getLong("currentPlayer",-1))
        val incorrects = game.totalWords-game.corrects

        imgActualPlayerR.setImageResource(user.imageId)
        lblActualPlayerR.text=user.userName
        lbl2.text=game.corrects.toString()
        lbl4.text=incorrects.toString()
        lbl6.text=game.points.toString()
    }

    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarR)
    }

}
