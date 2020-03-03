package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.data.UsersDatabase
import es.iessaladillo.pedrojoya.stroop.data.pojo.UserAndGame
import es.iessaladillo.pedrojoya.stroop.extensions.invisibleUnless
import kotlinx.android.synthetic.main.ranking_fragment.*

class RankingFragment : Fragment(R.layout.ranking_fragment) {

    companion object {
    }

    private lateinit var rankingAdapter: RankingFragmentAdapter

    private val viewmodel: RankingViewModel by viewModels {
        RankingViewModelFactory(
            UsersDatabase.getInstance
                (this.requireContext()).userGameDao, requireActivity().application
        )
    }

    val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        setupSpinner()

    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.fragments_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.InfoDialogDestination -> findNavController().navigate(
                    R.id.infoDialogFragment,
                    bundleOf(
                        getString(R.string.ARG_MESSAGE) to getString(R.string.ranking_help_description)
                    )
                )
            }
            true
        }
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbar)

    }

    private fun setupAdapter() {
        rankingAdapter = RankingFragmentAdapter(activity!!.application)
    }

    private fun setupSpinner() {
        when (settings.getString("prefRankingFilter", "All").toString().toLowerCase()) {
            "all" -> {
                spinnerModes.setSelection(0)
            }
            "time" -> {
                spinnerModes.setSelection(1)
            }
            "attempts" -> {
                spinnerModes.setSelection(2)
            }
        }
        spinnerModes.onItemSelectedListener =  object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (spinnerModes.selectedItem.toString().toLowerCase()) {
                    "all" -> {
                        viewmodel.queryAllUserGames()
                    }
                    "time" -> {
                        viewmodel.queryAllUserGamesTime()
                    }
                    "attempts" -> {
                        viewmodel.queryAllUserGamesAttempts()
                    }
                }
            }
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewmodel.userGames.observe(this) {
            showPlayers(it)
        }
    }

    private fun setupRecyclerView() {
        lstGames.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = rankingAdapter

        }
    }

    private fun showPlayers(userGames: List<UserAndGame>) {
        lstGames.post {
            rankingAdapter.submitList(userGames)
            imgEmptyR.invisibleUnless(userGames.isEmpty())
            lblEmptyViewR.invisibleUnless(userGames.isEmpty())
        }

    }

}
