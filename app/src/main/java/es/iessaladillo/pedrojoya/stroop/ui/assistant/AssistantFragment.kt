package es.iessaladillo.pedrojoya.stroop.ui.assistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.entity.Page
import kotlinx.android.synthetic.main.assistant_fragment.*


class AssistantFragment : Fragment(R.layout.assistant_fragment) {

    private lateinit var assistantAdapter: AssistantFragmentAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupAdapter()
        setupViewPager()
        setupTabLayout()
    }

    private fun setupTabLayout() {
        TabLayoutMediator(tabLayout, viewPager) { _,_ -> }.attach()
    }

    private fun setupAdapter() {
        val pageList : ArrayList<Page> = arrayListOf()
        pageList.add(Page(R.drawable.logo,getString(R.string.assistant_stroopDescription),R.color.stroopOption))
        pageList.add(Page(R.drawable.ic_play_black_24dp,R.string.assistant_playDescription.toString(),R.color.playOption))
        pageList.add(Page(R.drawable.ic_settings_black_24dp,R.string.assistant_settingsDescription.toString(),R.color.settingsOption))
        pageList.add(Page(R.drawable.ic_ranking_black_24dp,R.string.assistant_rankingDescription.toString(),R.color.rankingOption))
        pageList.add(Page(R.drawable.ic_assistant_black_24dp,R.string.assistant_assistantDescription.toString(),R.color.assistantOption))
        pageList.add(Page(R.drawable.ic_player_black_24dp,R.string.assistant_playerDescription.toString(),R.color.playerOption))
        pageList.add(Page(R.drawable.ic_about_black_24dp,R.string.assistant_aboutDescription.toString(),R.color.aboutOption))
        pageList.add(Page(R.drawable.ic_finish_black_24dp,R.string.assistant_finishDescription.toString(),R.color.finishOption))

        assistantAdapter = AssistantFragmentAdapter(pageList)
    }


    private fun setupViewPager() {
        viewPager.run {
            adapter = assistantAdapter
        }
    }

}
