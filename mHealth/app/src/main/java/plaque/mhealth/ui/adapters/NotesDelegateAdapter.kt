package plaque.mhealth.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_item_meds.view.*
import plaque.mhealth.commons.inflate
import plaque.mhealth.R
import plaque.mhealth.model.CyclicNote

/**
 * Created by szymon on 13.09.17.
 */
class NotesDelegateAdapter(val viewActions: onViewSelectedListener): ViewTypeDelegateAdapter {

    interface onViewSelectedListener {
        fun onItemSelected(item: CyclicNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
            = NotesViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NotesViewHolder
        holder.bind(item as CyclicNote)
    }

    inner class NotesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
         parent.inflate(R.layout.rv_item_meds)) {

        private val title = itemView.note_name
        private val active = itemView.active

        fun bind(item: CyclicNote) = with(itemView) {
            title.text = item.title
            active.isChecked = item.active

            super.itemView.setOnClickListener { viewActions.onItemSelected(item) }
        }
    }
}