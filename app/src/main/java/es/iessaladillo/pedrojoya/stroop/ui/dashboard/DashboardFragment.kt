package es.iessaladillo.pedrojoya.stroop.ui.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.data.entity.Card
import kotlinx.android.synthetic.main.assistant_fragment.*
import kotlinx.android.synthetic.main.dashboard_fragment.*
import kotlinx.android.synthetic.main.dashboard_fragment.toolbar


class DashboardFragment : Fragment(R.layout.dashboard_fragment) {

    private lateinit var viewModel: DashboardViewModel

    private lateinit var dashboardAdapter: DashboardFragmentAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupAdapter()
        setupRecyclerView()
        setupToolbar()
    }

    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupRecyclerView() {
        lstCards.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = dashboardAdapter

        }
    }

    private fun setupAdapter() {
        val cardList: ArrayList<Card> = arrayListOf()
        cardList.add(
            Card(
                R.drawable.ic_play_black_24dp,
                getString(R.string.dashboard_lblPlay),
                R.color.playOption
            )
        )
        cardList.add(
            Card(
                R.drawable.ic_settings_black_24dp,
                getString(R.string.dashboard_lblSettings),
                R.color.settingsOption
            )
        )
        cardList.add(
            Card(
                R.drawable.ic_ranking_black_24dp,
                getString(R.string.dashboard_lblRanking),
                R.color.rankingOption
            )
        )
        cardList.add(
            Card(
                R.drawable.ic_assistant_black_24dp,
                getString(R.string.dashboard_lblAssistant),
                R.color.assistantOption
            )
        )
        cardList.add(
            Card(
                R.drawable.ic_player_black_24dp,
                getString(R.string.dashboard_lblPlayer),
                R.color.playerOption
            )
        )
        cardList.add(
            Card(
                R.drawable.ic_about_black_24dp,
                getString(R.string.dashboard_lblAbout),
                R.color.aboutOption
            )
        )
        dashboardAdapter = DashboardFragmentAdapter(cardList).also {
            it.onItemClickListener = { position -> navigateToPosition(position) }
        }
    }

    private fun navigateToPosition(position: Int) {
        when (position) {
            1 -> findNavController().navigate(R.id.navigateToSettings)
            3 -> findNavController().navigate(R.id.navigateToAssistant)
            4 -> findNavController().navigate(R.id.navigateToPlayers)
            5 -> findNavController().navigate(R.id.navigateToAbout)
        }
    }

}

