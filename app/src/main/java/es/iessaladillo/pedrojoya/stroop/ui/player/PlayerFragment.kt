package es.iessaladillo.pedrojoya.stroop.ui.player


import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.base.SharedPreferenceLongLiveData
import es.iessaladillo.pedrojoya.stroop.data.UsersDatabase
import es.iessaladillo.pedrojoya.stroop.data.entity.User
import es.iessaladillo.pedrojoya.stroop.extensions.invisibleUnless
import kotlinx.android.synthetic.main.player_fragment.*
import kotlinx.android.synthetic.main.player_fragment.toolbar

/**
 * A simple [Fragment] subclass.
 */
class PlayerFragment : Fragment(R.layout.player_fragment) {
    //ProgressBar para barra game


    val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }

    private lateinit var playerAdapter: PlayerFragmentAdapter

    

    private val viewmodel: PlayerViewmodel by viewModels {
        PlayerViewModelFactory(
            UsersDatabase.getInstance
                (this.requireContext()).userDao, requireActivity().application
        )
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.inflateMenu(R.menu.fragments_menu)
        setupViews()
    }

    private fun setupViews() {
        setupBtns()
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        observeLiveData()
        setupCurrentPlayer()
    }

    private fun setupCurrentPlayer() {
        //settings.getLong("currentUser", -1)

        //viewmodel.currentUser.value=currentUser

        val currentUser = settings.getLong("currentPlayer", -1)
        if(viewmodel.currentUserId.value!=-1L){
            viewmodel.queryUser(viewmodel.currentUserId.value!!)
            lblActualPlayer.text = viewmodel.currentUser.value!!.userName
            imgActualPlayer.setImageResource(viewmodel.currentUser.value!!.imageId)
        }else{
            lblActualPlayer.text = getString(R.string.player_selection_no_player_selected)
            imgActualPlayer.setImageResource(R.drawable.logo)
        }






    }



    private fun observeLiveData() {
        viewmodel.users.observe(this) {
            showPlayers(it)
        }
    }

    private fun showPlayers(users: List<User>) {
        lstPlayers.post {
            playerAdapter.submitList(users)
            imgAddPlayer.invisibleUnless(users.isEmpty())
            lblEmptyView.invisibleUnless(users.isEmpty())
        }

    }


    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbar)
        // (requireActivity() as AppCompatActivity).supportActionBar?.run {
        //     setDisplayHomeAsUpEnabled(true)
        // }
    }

    private fun setupBtns() {
        fabAdd.setOnClickListener { navigateToAddPlayer() }
        imgAddPlayer.setOnClickListener { navigateToAddPlayer() }
        lblEmptyView.setOnClickListener { navigateToAddPlayer() }

        btnEdit.visibility=View.VISIBLE
        btnEdit.setOnClickListener { navigateToEdit(viewmodel.currentUserId.value!!) }
    }

    private fun setupRecyclerView() {
        lstPlayers.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = playerAdapter

        }
    }

    private fun setupAdapter() {
        playerAdapter = PlayerFragmentAdapter().also {
            it.onItemClickListener = { selectCurrentPlayer(it) }
        }
    }

    private fun selectCurrentPlayer(position: Int) {
        settings.edit {
            putLong("currentPlayer", playerAdapter.userList[position].userId)
        }
    }



    private fun navigateToEdit(userId: Long) {
        findNavController().navigate(R.id.navigateToEditPlayer, bundleOf(
            getString(R.string.ARGS_USER_ID) to userId
        ))
    }


    private fun navigateToAddPlayer() {
        findNavController().navigate(R.id.navigateToAddPlayer)

    }


}
