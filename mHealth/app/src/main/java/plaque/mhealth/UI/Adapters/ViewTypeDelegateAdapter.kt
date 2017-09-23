package plaque.mhealth.UI.Adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by szymon on 13.09.17.
 */
interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}