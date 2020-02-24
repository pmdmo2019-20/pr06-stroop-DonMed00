package es.iessaladillo.pedrojoya.stroop.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import kotlinx.android.synthetic.main.assistant_fragment.toolbar

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment(R.layout.about_fragment) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
    }
    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.fragments_menu)
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbar)

    }


}
