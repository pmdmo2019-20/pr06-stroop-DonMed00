package es.iessaladillo.pedrojoya.stroop.ui.player


import android.content.SharedPreferences
import android.os.Bundle
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
        val currentUser = SharedPreferenceLongLiveData(
            settings,
            "currentUser",
            -1
        ).getValueFromPreferences("currentUser", -1)
        //viewmodel.currenUser.value=currentUser
        val vmUser = viewmodel.queryUser(viewmodel.currentUser.value!!).toString()
        if(vmUser.isNullOrBlank()){
            lblActualPlayer.text = (-1).toString()
        }else{
            lblActualPlayer.text = vmUser
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
            it.onItemClickListener = { selectCurrentPlayer(it)}
        }
    }

    private fun selectCurrentPlayer(position: Int) {
        viewmodel.currentUser.observe(this){
            playerAdapter.userList[position].userId
        }
    }


    private fun navigateToAddPlayer() {
        findNavController().navigate(R.id.navigateToAddPlayer)

    }


}
