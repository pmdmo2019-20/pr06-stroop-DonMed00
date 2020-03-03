package es.iessaladillo.pedrojoya.stroop.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.entity.Card
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_dashboard_item.*

class DashboardFragmentAdapter(private var cardList: ArrayList<Card>) :
    RecyclerView.Adapter<DashboardFragmentAdapter.ViewHolder>() {

    var onItemClickListener: ((Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.card_dashboard_item, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card: Card = cardList[position]
        holder.bind(card)


    }


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {


        init {
            containerView.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }

        }

        fun bind(card: Card) {
            card.run {
                imgBtn.setImageResource(imgId)
                lblBtn.text=text
                containerView.run {
                    imgBtn.setColorFilter(context.resources.getColor(colorId))
                    lblBtn.setTextColor(context.resources.getColor(colorId))
                }




            }
        }
    }
}