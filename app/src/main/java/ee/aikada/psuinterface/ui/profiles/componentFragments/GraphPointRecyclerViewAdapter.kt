package ee.aikada.psuinterface.ui.profiles.componentFragments

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.R


class GraphPointRecyclerViewAdapter(
    private val items: List<Array<Float>>,
    private val listener: (Any) -> Unit
) :
    RecyclerView.Adapter<GraphPointViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GraphPointViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GraphPointViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: GraphPointViewHolder, position: Int) {
        val point: Array<Float> = items[position]
        holder.bind(
            point, listener, holder.adapterPosition
        )
    }
}

class GraphPointViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_graph_point, parent, false)) {
    private val TAG = GraphPointViewHolder::class.java.simpleName

    private var mPoint: TextView? = null
    private var mRemoveButton: ImageButton? = null

    init {
        mPoint = itemView.findViewById(R.id.textView_graph_point)
        mRemoveButton = itemView.findViewById(R.id.imageButton_graph_point_remove)
    }

    fun bind(
        point: Array<Float>,
        listener: (Any) -> Unit,
        position: Int
    ) {
        Log.d(TAG, "bind")
        mPoint?.text = point[0].toString() + ", " + point[1].toString()
        mRemoveButton?.setOnClickListener { listener(position) }

    }

}