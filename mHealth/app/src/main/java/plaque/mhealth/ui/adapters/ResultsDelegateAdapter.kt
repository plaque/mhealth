package plaque.mhealth.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_item_results.view.*
import plaque.mhealth.R
import plaque.mhealth.commons.inflate
import plaque.mhealth.model.Result

/**
 * Created by szymon on 01.11.17.
 */
class ResultsDelegateAdapter(val viewActions: onViewSelectedListener):ViewTypeDelegateAdapter {

    interface onViewSelectedListener{
        fun onItemSelected(result: Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
            = PeopleViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as PeopleViewHolder
        holder.bind(item as Result)
    }

    inner class PeopleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.rv_item_results)) {

        private val name = itemView.result_name

        fun bind(item: Result) = with(itemView) {
            name.text = item.title

            super.itemView.setOnClickListener { viewActions.onItemSelected(item) }
        }
    }
}