package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.pojo.UserAndGame
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.game_ranking_item.*


class RankingFragmentAdapter ( var application : Application) :
    RecyclerView.Adapter<RankingFragmentAdapter.ViewHolder>() {

    var onItemClickListener: ((Int) -> Unit)? = null

    var userGamesList: List<UserAndGame> = arrayListOf()
    var currentPosition :Int = -1


    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.game_ranking_item, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return userGamesList.size
    }

    fun submitList(newList: List<UserAndGame>) {
        userGamesList = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userGames: UserAndGame = userGamesList[position]
        holder.bind(userGames)


    }


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {



        init {
            containerView.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }

        }

        fun bind(userGames: UserAndGame) {
            userGames.run {
                imgAvatR.setImageResource(imageId)
                lblName.text=userName
                lblGameMode.text=application.getString(R.string.ranking_item_gameMode2,gameMode)
                lblTime.text=application.getString(R.string.ranking_item_time,totalTime.toString())
                lblWords.text=application.getString(R.string.ranking_item_words,totalWords.toString())


                lblCorrects.text=application.getString(R.string.ranking_item_correct,corrects.toString())
                lblPoints2.text=points.toString()
            }
        }
    }
}
