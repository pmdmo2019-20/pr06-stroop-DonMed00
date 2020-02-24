package es.iessaladillo.pedrojoya.stroop.ui.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import es.iessaladillo.pedrojoya.stroop.R


class InfoDialogFragment : DialogFragment() {

    private val message: String? by lazy {
        arguments!!.getString(getString(R.string.ARG_MESSAGE))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
                .setTitle(R.string.help_title)
                .setMessage(message)
                .setPositiveButton(R.string.help_accept) { _, _ -> }


            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
