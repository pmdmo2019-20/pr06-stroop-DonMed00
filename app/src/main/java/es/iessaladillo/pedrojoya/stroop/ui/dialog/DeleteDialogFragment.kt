package es.iessaladillo.pedrojoya.stroop.ui.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.content.edit
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.NO_PLAYER
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.UsersDatabase
import es.iessaladillo.pedrojoya.stroop.ui.player.PlayerViewmodel

/**
 * A simple [Fragment] subclass.
 */
class DeleteDialogFragment : DialogFragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.player_deletion_title)
            builder.setMessage(R.string.player_deletion_message)
                .setPositiveButton(R.string.player_deletion_yes) { _, _ ->
                    val userDao = UsersDatabase.getInstance(this.requireContext()).userDao

                     val userId: Long by lazy {
                        arguments!!.getLong(getString(R.string.ARGS_USER_DELETE))
                    }
                    userDao.deleteUser(userDao.queryUser(userId))
                    val settings: SharedPreferences by lazy {
                        PreferenceManager.getDefaultSharedPreferences(activity)
                    }
                    settings.edit {
                        putLong("currentPlayer", NO_PLAYER)
                    }
                    PlayerViewmodel(userDao,activity!!.application).setCurrentUserId(-1)
                    findNavController().navigate(R.id.comeBack)
                }
                .setNegativeButton(R.string.player_deletion_no) { _, _ ->

                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}
