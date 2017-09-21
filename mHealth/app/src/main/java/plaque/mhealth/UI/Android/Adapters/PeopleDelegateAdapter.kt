package plaque.mhealth.UI.Android.Adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_item_patients.view.*
import plaque.mhealth.Commons.inflate
import plaque.mhealth.Model.User
import plaque.mhealth.R

/**
 * Created by szymon on 14.09.17.
 */
class PeopleDelegateAdapter:ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = PeopleViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as PeopleViewHolder
        holder.bind(item as User)
    }

    inner class PeopleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.rv_item_patients)) {

        private val name = itemView.patient_name

        fun bind(item: User) = with(itemView) {
            name.text = item.email
        }
    }
}