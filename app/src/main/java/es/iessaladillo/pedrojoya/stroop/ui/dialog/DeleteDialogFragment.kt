package es.iessaladillo.pedrojoya.stroop.ui.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import es.iessaladillo.pedrojoya.stroop.R

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
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.player_deletion_title)
            builder.setMessage(R.string.player_deletion_message)
                .setPositiveButton(R.string.player_deletion_yes) { _, _ ->

                }
                .setNegativeButton(R.string.player_deletion_no) { _, _ ->

                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}
