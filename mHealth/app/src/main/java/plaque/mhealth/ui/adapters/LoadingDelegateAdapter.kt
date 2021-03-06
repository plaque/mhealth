package plaque.mhealth.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import plaque.mhealth.commons.inflate
import plaque.mhealth.R

/**
 * Created by szymon on 13.09.17.
 */
class LoadingDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {

    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
                       parent.inflate(R.layout.item_loading)) {

    }
}