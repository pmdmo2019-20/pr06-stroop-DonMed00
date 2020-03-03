package es.iessaladillo.pedrojoya.stroop.ui.playerAdd


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.base.observeEvent
import es.iessaladillo.pedrojoya.stroop.data.UsersDatabase
import es.iessaladillo.pedrojoya.stroop.extensions.hideSoftKeyboard
import kotlinx.android.synthetic.main.add_player_fragment.*
import kotlinx.android.synthetic.main.add_player_fragment.imgActualPlayer

/**
 * A simple [Fragment] subclass.
 */
class PlayerAddFragment : Fragment(R.layout.add_player_fragment) {

    private lateinit var playerAddAdapter: PlayerAddFragmentAdapter

    private val viewmodel: PlayerAddViewmodel by viewModels {
        PlayerAddViewmodelFactory(
            UsersDatabase.getInstance(this.requireContext()).userDao,
            requireActivity().application
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        showPlayers(avatars)
        fabSave.setOnClickListener { save() }
        observeEvents()


    }

    private fun observeEvents() {
        viewmodel.message.observeEvent(this) {
            Snackbar.make(lstAvatars, it, Snackbar.LENGTH_SHORT).show()
        }
        viewmodel.onBack.observeEvent(this) {
            if (it) {
                activity!!.onBackPressed()
            }
        }
        viewmodel.currentPlayerId.observe(this) {
            if (it == 0L) {
                imgActualPlayer.setImageResource(R.drawable.logo)
            } else {
                imgActualPlayer.setImageResource(it.toInt())

            }
        }
    }

    private fun save() {
        if (lblActualPlayerEdit.text.toString().isNotEmpty() && viewmodel.currentPlayerId.value != 0L) {
            viewmodel.addUser(
                lblActualPlayerEdit.text.toString(),
                viewmodel.currentPlayerId.value!!.toInt()
            )
            lblActualPlayerEdit.hideSoftKeyboard()
        }
    }

    private fun showPlayers(avatars: List<Int>) {
        playerAddAdapter.submitList(avatars)

    }


    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.fragments_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.InfoDialogDestination -> findNavController().navigate(
                    R.id.infoDialogFragment,
                    bundleOf(
                        getString(R.string.ARG_MESSAGE) to getString(R.string.player_creation_help_description)
                    )
                )
            }
            true
        }
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbar)

    }

    private fun setupRecyclerView() {
        lstAvatars.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 3)
            adapter = playerAddAdapter

        }
    }

    private fun setupAdapter() {
        playerAddAdapter = PlayerAddFragmentAdapter()
            .also {
                it.onItemClickListener = { position ->
                    selectAvatar(position)
                    playerAddAdapter.currentPosition = position
                    playerAddAdapter.notifyDataSetChanged()
                }
            }
    }

    private fun selectAvatar(position: Int) {
        viewmodel.setCurrentPlayerId(avatars[position].toLong())

    }


}
