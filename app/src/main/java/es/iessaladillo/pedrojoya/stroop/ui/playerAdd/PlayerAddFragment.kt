package es.iessaladillo.pedrojoya.stroop.ui.playerAdd


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import es.iessaladillo.pedrojoya.stroop.NO_PLAYER

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.data.UserDao
import es.iessaladillo.pedrojoya.stroop.data.UsersDatabase
import es.iessaladillo.pedrojoya.stroop.data.entity.User
import es.iessaladillo.pedrojoya.stroop.ui.player.PlayerViewModelFactory
import es.iessaladillo.pedrojoya.stroop.ui.player.PlayerViewmodel
import kotlinx.android.synthetic.main.add_player_fragment.*
import kotlinx.android.synthetic.main.avatar_player_item.view.*
import kotlinx.android.synthetic.main.player_fragment.toolbar

/**
 * A simple [Fragment] subclass.
 */
class PlayerAddFragment : Fragment(R.layout.add_player_fragment) {

    private lateinit var playerAddAdapter: PlayerAddFragmentAdapter

    private val viewmodel : PlayerAddViewmodel by viewModels{
        PlayerAddViewmodelFactory(UsersDatabase.getInstance(this.requireContext()).userDao,requireActivity().application)
    }
    private var currentAvatar: Int = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        fabSave.setOnClickListener { save() }
    }

    private fun save() {
        if (txtAvatar.text.toString().isNotEmpty() && currentAvatar != 0) {
            viewmodel.addUser(txtAvatar.text.toString(),currentAvatar)
        }
    }


    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupRecyclerView() {
        lstAvatars.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = playerAddAdapter

        }
    }

    private fun setupAdapter() {
        playerAddAdapter = PlayerAddFragmentAdapter()
            .also {
                it.onItemClickListener = { position -> selectAvatar(position) }
            }
    }

    private fun selectAvatar(position: Int) {
        currentAvatar = avatars[position]
        lstAvatars[position].viewCheck.visibility = View.VISIBLE
    }


}
