package es.iessaladillo.pedrojoya.stroop.ui.assistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.entity.Page
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.page_assistant_item.view.*

class AssistantFragmentAdapter(val pageList: ArrayList<Page>) :
    RecyclerView.Adapter<AssistantFragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.page_assistant_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val page: Page = pageList[position]
        holder.bind(page)
    }


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(page: Page) {
            page.run {
                containerView.imgIcon.setImageResource(page.imageId)
                containerView.lblContent.text = page.text
                containerView.setBackgroundResource(page.colorId)
            }
        }
    }
}