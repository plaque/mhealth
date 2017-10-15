package plaque.mhealth.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_item_user.view.*
import plaque.mhealth.commons.inflate
import plaque.mhealth.model.User
import plaque.mhealth.R

/**
 * Created by szymon on 14.09.17.
 */
class PeopleDelegateAdapter(val viewActions: onViewSelectedListener):ViewTypeDelegateAdapter {

    interface onViewSelectedListener{
        fun onItemSelected(pupil: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
            = PeopleViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as PeopleViewHolder
        holder.bind(item as User)
    }

    inner class PeopleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.rv_item_user)) {

        private val name = itemView.user_name

        fun bind(item: User) = with(itemView) {
            name.text = "${item.name} ${item.surname}"
        }
    }
}