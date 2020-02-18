package es.iessaladillo.pedrojoya.stroop.ui.player


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import kotlinx.android.synthetic.main.player_fragment.*
import kotlinx.android.synthetic.main.player_fragment.toolbar

/**
 * A simple [Fragment] subclass.
 */
class PlayerFragment : Fragment(R.layout.player_fragment) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupBtns()
        setupToolbar()
    }


    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupBtns() {
        fabAdd.setOnClickListener { navigateToAddPlayer() }
        imgAddPlayer.setOnClickListener { navigateToAddPlayer() }
        lblNoPlayersYet.setOnClickListener { navigateToAddPlayer() }
    }

    private fun navigateToAddPlayer() {
        findNavController().navigate(R.id.navigateToAddPlayer)
    }


}
