package plaque.mhealth.ui.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import plaque.mhealth.model.Result

/**
 * Created by szymon on 01.11.17.
 */
class ResultsAdapter(listener: ResultsDelegateAdapter.onViewSelectedListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object: ViewType{
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.RESULTS, ResultsDelegateAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
            = delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = delegateAdapters.get(viewType).onCreateViewHolder(parent)

    fun addResults(results: List<Result>) {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(results)
        notifyItemRangeChanged(initPosition, items.size)
    }

    fun addResult(result: Result){
        items.add(result)
        notifyItemInserted(items.size)
    }

    fun getResults() = items

}