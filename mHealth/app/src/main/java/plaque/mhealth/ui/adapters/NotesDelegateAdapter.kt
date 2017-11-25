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

    var isClickable = true

    interface onViewSelectedListener {
        fun onItemSelected(item: CyclicNote, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
            = NotesViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NotesViewHolder
        if(isClickable) {
            holder.bind(item as CyclicNote)
        }else{
            holder.bindNotClickable(item as CyclicNote)
        }
    }

    fun updateClickability(state: Boolean){
        this.isClickable = state
    }

    inner class NotesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
         parent.inflate(R.layout.rv_item_meds)) {

        private val title = itemView.note_name
        private val active = itemView.active

        fun bind(item: CyclicNote) = with(itemView) {
            title.text = item.title
            active.isChecked = item.active

            super.itemView.setOnClickListener { viewActions.onItemSelected(item, super.getPosition()) }
        }

        fun bindNotClickable(item: CyclicNote) = with(itemView){
            title.text = item.title
            active.isChecked = item.active
            active.isClickable = false

            super.itemView.setOnClickListener { viewActions.onItemSelected(item, super.getPosition()) }
        }
    }
}