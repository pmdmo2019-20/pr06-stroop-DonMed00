package es.iessaladillo.pedrojoya.stroop.ui.dashboard

import android.content.SharedPreferences
import android.os.Bundle
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
import es.iessaladillo.pedrojoya.stroop.data.entity.Card

import kotlinx.android.synthetic.main.dashboard_fragment.*
import kotlinx.android.synthetic.main.dashboard_fragment.toolbarR


class DashboardFragment : Fragment(R.layout.dashboard_fragment) {

    private val viewmodel: DashboardViewModel by viewModels {
        DashboardViewmodelFactory(
            UsersDatabase.getInstance(this.requireContext()).userDao,
            requireActivity().application
        )
    }
    private lateinit var dashboardAdapter: DashboardFragmentAdapter

    val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewmodel.setCurrentUserId(settings.getLong("currentPlayer", -1))
        setupViews()


    }

    private fun setupViews() {
        observeFirstInstall()
        setupAdapter()
        setupRecyclerView()
        setupToolbar()
        observeUser()
    }

    private fun observeFirstInstall() {
        val firstTime = settings.getBoolean("firstTime", true)
        if (firstTime) {
            findNavController().navigate(R.id.navigateToAssistant)
        }
        settings.edit {
            putBoolean("firstTime", false)
        }

    }

    private fun observeUser() {
        SharedPreferenceLongLiveData(settings, "currentPlayer", -1L).observe(this) {
            if (it != -1L) {
                val user = viewmodel.queryUser(it)
                imgActualPlayer.setImageResource(user.imageId)
                lblActualPlayerDash.text = user.userName
            } else {
                imgActualPlayer.setImageResource(R.drawable.logo)
                lblActualPlayerDash.text = getString(R.string.app_name)
            }
            setupLblAndImg()
        }
    }

    private fun setupLblAndImg() {
        imgActualPlayer.setOnClickListener { findNavController().navigate(R.id.navigateToPlayers) }
        lblActualPlayerDash.setOnClickListener { findNavController().navigate(R.id.navigateToPlayers) }

    }

    private fun setupToolbar() {
        toolbarR.inflateMenu(R.menu.fragments_menu)
        toolbarR.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.InfoDialogDestination -> findNavController().navigate(
                    R.id.infoDialogFragment,
                    bundleOf(
                        getString(R.string.ARG_MESSAGE) to getString(R.string.dashboard_help_description)
                    )
                )
            }
            true
        }
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarR)

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
            0 -> if(settings.getLong("currentPlayer",-1)==-1L){
                findNavController().navigate(R.id.navigateToPlayers)
            }else{
                findNavController().navigate(R.id.navigateToGame)
            }
            1 -> findNavController().navigate(R.id.navigateToSettings)
            2 -> findNavController().navigate(R.id.navigateToRanking)
            3 -> findNavController().navigate(R.id.navigateToAssistant)
            4 -> findNavController().navigate(R.id.navigateToPlayers)
            5 -> findNavController().navigate(R.id.navigateToAbout)
        }
    }

}

