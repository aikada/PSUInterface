package ee.itcollege.aikada.psuinterface.ui.status

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ee.itcollege.aikada.psuinterface.R
import ee.itcollege.aikada.psuinterface.models.StatusItem
import kotlinx.android.synthetic.main.recycler_status_item.view.*

/**
 * TODO: document your custom view class.
 */
class StatusRecyclerViewAdapter(private val items : List<StatusItem>) : RecyclerView.Adapter<StatusItemViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_status_item, parent, false))
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StatusItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: StatusItemViewHolder, position: Int) {
        val statusItem: StatusItem = items[position]
        holder.bind(statusItem)
    }

}

//class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
//    val textViewStatusChannelName = view.textView_status_channel_name
//}

class StatusItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_status_item, parent, false)) {
    private var mChannelName: TextView? = null

    init {
        mChannelName = itemView.findViewById(R.id.textView_status_channel_name)
    }

    fun bind(statusItem: StatusItem) {
        mChannelName?.text = statusItem.channelName
    }

}
