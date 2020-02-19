package es.iessaladillo.pedrojoya.stroop.ui.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.entity.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.avatar_player_item.*
import kotlinx.android.synthetic.main.avatar_player_item.viewCheck
import kotlinx.android.synthetic.main.user_player_item.*

class PlayerFragmentAdapter(private val allUsers: LiveData<List<User>>) :
    RecyclerView.Adapter<PlayerFragmentAdapter.ViewHolder>() {

    var onItemClickListener: ((Int) -> Unit)? = null



    init {
        setHasStableIds(true)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.user_player_item, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return allUsers.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user : User = allUsers.value!![position]
        holder.bind(user)


    }


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {



        init {
            containerView.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }

        }

        fun bind(user : User) {
            user.run {
                lblUser.text=user.userName
                imgUser.setImageResource(user.imageId)
                viewCheck.visibility= View.INVISIBLE
            }

        }
    }
}