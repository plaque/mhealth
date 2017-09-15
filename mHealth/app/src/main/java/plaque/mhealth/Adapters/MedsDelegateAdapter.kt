package plaque.mhealth.Adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_item_meds.view.*
import plaque.mhealth.Commons.inflate
import plaque.mhealth.Model.Note
import plaque.mhealth.Model.OneTimeNote
import plaque.mhealth.R

/**
 * Created by szymon on 13.09.17.
 */
class MedsDelegateAdapter: ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MedsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MedsViewHolder
        holder.bind(item as Note)
    }

    inner class MedsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
         parent.inflate(R.layout.rv_item_meds)) {

        private val title = itemView.note_name
        private val active = itemView.active

        fun bind(item: Note) = with(itemView) {
            title.text = item.title
            active.isChecked = item.active
        }
    }
}