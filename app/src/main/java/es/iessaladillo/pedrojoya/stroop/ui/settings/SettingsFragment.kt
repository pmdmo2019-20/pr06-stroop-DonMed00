package es.iessaladillo.pedrojoya.stroop.ui.settings


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.ui.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.assistant_fragment.*
import kotlinx.android.synthetic.main.settings_fragment.*
import kotlinx.android.synthetic.main.settings_fragment.toolbar

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment(R.layout.settings_fragment) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
    }

    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }
    }

}
