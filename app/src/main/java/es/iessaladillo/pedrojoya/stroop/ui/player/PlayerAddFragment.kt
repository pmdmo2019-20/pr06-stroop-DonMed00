package es.iessaladillo.pedrojoya.stroop.ui.player


import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import kotlinx.android.synthetic.main.add_player_fragment.*
import kotlinx.android.synthetic.main.avatar_player_item.view.*
import kotlinx.android.synthetic.main.player_fragment.toolbar

/**
 * A simple [Fragment] subclass.
 */
class PlayerAddFragment : Fragment(R.layout.add_player_fragment) {

    private lateinit var playerAddAdapter: PlayerAddFragmentAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
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
            layoutManager = GridLayoutManager(activity, 3)
            adapter = playerAddAdapter

        }
    }

    private fun setupAdapter() {
        playerAddAdapter = PlayerAddFragmentAdapter().also {
            it.onItemClickListener = { position -> selectAvatar(position) }
        }
    }

    private fun selectAvatar(position: Int) {
        lstAvatars[position].viewCheck.visibility= View.VISIBLE
    }


}
