package es.iessaladillo.pedrojoya.stroop.ui.player


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.data.UsersDatabase
import kotlinx.android.synthetic.main.player_fragment.*
import kotlinx.android.synthetic.main.player_fragment.toolbar

/**
 * A simple [Fragment] subclass.
 */
class PlayerFragment : Fragment(R.layout.player_fragment) {

    private lateinit var playerAdapter: PlayerFragmentAdapter


    private val viewmodel : PlayerViewmodel by viewModels{
        PlayerViewModelFactory(UsersDatabase.getInstance(this.requireContext()).userDao,requireActivity().application)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupBtns()
        setupToolbar()

        if(viewmodel.queryAllUsers().value.isNullOrEmpty()){

        }else{
            lblNoPlayersYet.visibility=View.INVISIBLE
            setupAdapter()
            setupRecyclerView()
        }
        lblPlayer.text=viewmodel.queryCount().value.toString()
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

    private fun setupRecyclerView() {
        lstPlayers.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 3)
            adapter = playerAdapter

        }
    }

    private fun setupAdapter() {
        playerAdapter = PlayerFragmentAdapter(viewmodel.queryAllUsers())
            .also {
                it.onItemClickListener = {  }
            }
    }

    private fun navigateToAddPlayer() {
        findNavController().navigate(R.id.navigateToAddPlayer)
    }


}
