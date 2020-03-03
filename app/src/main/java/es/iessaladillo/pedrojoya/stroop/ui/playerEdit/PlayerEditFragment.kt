package es.iessaladillo.pedrojoya.stroop.ui.playerEdit


import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
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
import kotlinx.android.synthetic.main.player_edit_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class PlayerEditFragment : Fragment(R.layout.player_edit_fragment) {

    private lateinit var playerEditFragment: PlayerEditFragmentAdapter

    private val viewmodel: PlayerEditViewmodel by viewModels {
        PlayerEditViewmodelFactory(
            UsersDatabase.getInstance(this.requireContext()).userDao,
            requireActivity().application
        )
    }
    private val userId: Long by lazy {
        arguments!!.getLong(getString(R.string.ARGS_USER_ID))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        viewmodel.setCurrentPlayerAvatar(avatars.indexOf(viewmodel.queryUser(userId).imageId))
    }

    private fun setupViews() {
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        showPlayers(avatars)
        setupLbl()
        fabSave.setOnClickListener { save() }
        observeEvents()


    }

    private fun setupLbl() {
        imgActualPlayerEdit.setImageResource(viewmodel.queryUser(userId).imageId)
        lblActualPlayerEdit.setText(viewmodel.queryUser(userId).userName)
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
    }

    private fun save() {
        //viewmodel.currentPlayerAvatar.observe(this) {
        viewmodel.updateUser(
            userId,
            lblActualPlayerEdit.text.toString(),
            avatars[viewmodel.currentPlayerAvatar.value!!]
        )
        // }

        lblActualPlayerEdit.hideSoftKeyboard()

    }

    private fun showPlayers(avatars: List<Int>) {
        playerEditFragment.submitList(avatars)

    }


    private fun setupToolbar() {
        toolbarR.inflateMenu(R.menu.edit_menu)
        toolbarR.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.deleteDestination -> findNavController().navigate(R.id.deleteDialogFragment,bundleOf(
                    getString(R.string.ARGS_USER_DELETE) to userId))

                R.id.InfoDialogDestination -> findNavController().navigate(R.id.infoDialogFragment,bundleOf(
                    getString(R.string.ARG_MESSAGE) to getString(R.string.player_edition_help_description)))
            }
            true
        }

        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarR)

    }

    private fun setupRecyclerView() {
        lstAvatars.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 3)
            adapter = playerEditFragment

        }
    }

    private fun setupAdapter() {
        playerEditFragment = PlayerEditFragmentAdapter()
            .also {
                it.onItemClickListener = { position -> selectAvatar(position)
                    playerEditFragment.currentPosition = position
                    playerEditFragment.notifyDataSetChanged()}
            }
    }

    private fun selectAvatar(position: Int) {
        viewmodel.setCurrentPlayerAvatar(position)
        viewmodel.currentPlayerAvatar.observe(this) {
            imgActualPlayerEdit.setImageResource(avatars[it])
        }
        //lstAvatars[position].viewCheck.visibility = View.VISIBLE
    }
}
