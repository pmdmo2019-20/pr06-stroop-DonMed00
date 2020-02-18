package es.iessaladillo.pedrojoya.stroop.ui.settings


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.preference.PreferenceFragmentCompat
import es.iessaladillo.pedrojoya.stroop.R

/**
 * A simple [Fragment] subclass.
 */
class SettingsListFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences,rootKey)
    }




}
