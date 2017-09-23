package plaque.mhealth.UI.Adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import plaque.mhealth.Model.Note


/**
 * Created by szymon on 13.09.17.
 */
class NotesAdapter(listener: NotesDelegateAdapter.onViewSelectedListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object: ViewType{
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.MEDS, NotesDelegateAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
            = delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = delegateAdapters.get(viewType).onCreateViewHolder(parent)


    override fun getItemCount(): Int = items.size

    fun addNotes(notes: List<Note>) {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(notes)
        notifyItemRangeChanged(initPosition, items.size)
    }

    fun clearAndAddNotes(notes: List<Note>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(notes)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNotes(): List<Note> =
            items
                    .filter { it.getViewType() == AdapterConstants.MEDS }
                    .map { it as Note }



    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex


}