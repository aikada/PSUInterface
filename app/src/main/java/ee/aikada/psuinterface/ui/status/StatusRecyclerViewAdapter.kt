package ee.aikada.psuinterface.ui.status

import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.DTO.StatusFieldsDTO
import ee.aikada.psuinterface.DTO.StatusItemDTO
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.models.enums.ProfileType


class StatusRecyclerViewAdapter(private val items: List<StatusItemDTO>, val listener : (Any) -> Unit) :
    RecyclerView.Adapter<StatusItemViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return StatusItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: StatusItemViewHolder, position: Int) {
        val statusItem: StatusItemDTO = items[position]
        holder.bind(statusItem, listener)
    }

    fun setOnClickListener(listener: () -> Unit){
        listener() // or you could use optional if the lister is nullable "listener?.invoke()"
    }


}


class StatusItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_status_item, parent, false)) {
    private val TAG = StatusItemViewHolder::class.java.simpleName

    private var mChannelName: TextView? = null
    private var mStatusInfo: TextView? = null

    private var mField1: TextView? = null
    private var mField2: TextView? = null
    private var mField3: TextView? = null
    private var mField4: TextView? = null
    private var mT1: TextView? = null
    private var mT2: TextView? = null
    private var mT3: TextView? = null

    init {
        mChannelName = itemView.findViewById(R.id.textView_status_channel_name)
        mStatusInfo = itemView.findViewById(R.id.textView_status_channel_properties)

        mField1 = itemView.findViewById(R.id.textView_status_field1)
        mField2 = itemView.findViewById(R.id.textView_status_field2)
        mField3 = itemView.findViewById(R.id.textView_status_field3)
        mField4 = itemView.findViewById(R.id.textView_status_field4)
        mT1 = itemView.findViewById(R.id.textView_status_time_1)
        mT2 = itemView.findViewById(R.id.textView_status_time_2)
        mT3 = itemView.findViewById(R.id.textView_status_time_3)
    }

    fun bind(statusItem: StatusItemDTO, listener: (Any) -> Unit) {
        Log.d(TAG, "bind")
        setTextViews(statusItem)

        if (statusItem.statusFields == null) {
            itemView.setOnClickListener { listener(statusItem) }
        }
    }

    private fun setTextViews(statusItem: StatusItemDTO) {
        mChannelName?.text = statusItem.channelName

        if (statusItem.statusFields != null) {
            val statusFields: StatusFieldsDTO = statusItem.statusFields!!
            val currentProfile = ProfileType.valueOf(statusItem.profileType.toString())

            val statusText = itemView.context.getString(
                R.string.profile_info,
                statusItem.profileName, statusItem.profileGroup, statusItem.profileType
            )
            mStatusInfo?.text = statusText
            mStatusInfo?.setTypeface(null, Typeface.NORMAL)
            mField1?.text = currentProfile.formatField1(statusFields)
            mField2?.text = currentProfile.formatField2(statusFields)
            mField3?.text = currentProfile.formatField3(statusFields)
            mField4?.text = currentProfile.formatField4(statusFields)
            mT1?.text = currentProfile.formatTimeField1(statusFields)
            mT2?.text = currentProfile.formatTimeField2(statusFields)
            mT3?.text = currentProfile.formatTimeField3(statusFields)
        }
    }
}