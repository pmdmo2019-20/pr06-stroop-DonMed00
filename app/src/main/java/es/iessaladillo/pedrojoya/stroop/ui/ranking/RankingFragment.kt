package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener

import kotlinx.android.synthetic.main.settings_fragment.toolbar

class RankingFragment : Fragment(R.layout.ranking_fragment) {

    companion object {
        fun newInstance() = RankingFragment()
    }

    private lateinit var rankingAdapter: RankingFragmentAdapter

    private lateinit var viewmodel: RankingViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupAdapter()
    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.fragments_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.InfoDialogDestination -> findNavController().navigate(R.id.infoDialogFragment,
                    bundleOf(
                        getString(R.string.ARG_MESSAGE) to getString(R.string.ranking_help_description))
                )
            }
            true
        }
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbar)

    }


    private fun setupAdapter() {
        rankingAdapter = RankingFragmentAdapter()
    }

}
