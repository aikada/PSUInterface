package ee.aikada.psuinterface.ui.status

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.DTO.StatusFieldsDTO
import ee.aikada.psuinterface.DTO.StatusItemDTO
import ee.aikada.psuinterface.models.enums.ProfileType


/**
 * TODO: document your custom view class.
 */
class StatusRecyclerViewAdapter(private val items: List<StatusItemDTO>) :
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
        holder.bind(statusItem)
    }
}


class StatusItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_status_item, parent, false)) {
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

    fun bind(statusItem: StatusItemDTO) {
        val statusForDisplay: StatusFieldsDTO = statusItem.statusFields
        val currentProfile = ProfileType.valueOf(statusItem.profileType.toString())

        mChannelName?.text = statusItem.channelName
        mStatusInfo?.text =
            itemView.context.getString(
                R.string.profile_info,
                statusItem.profileName, statusItem.profileGroup, statusItem.profileType
            )
        mField1?.text = currentProfile.formatField1(statusForDisplay)
        mField2?.text = currentProfile.formatField2(statusForDisplay)
        mField3?.text = currentProfile.formatField3(statusForDisplay)
        mField4?.text = currentProfile.formatField4(statusForDisplay)
        mT1?.text = currentProfile.formatTimeField1(statusForDisplay)
        mT2?.text = currentProfile.formatTimeField2(statusForDisplay)
        mT3?.text = currentProfile.formatTimeField3(statusForDisplay)

    }
}