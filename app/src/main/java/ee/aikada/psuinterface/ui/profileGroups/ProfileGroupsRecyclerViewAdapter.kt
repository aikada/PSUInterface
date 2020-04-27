package ee.aikada.psuinterface.ui.profileGroups

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.DTO.ProfileGroupDTO
import ee.aikada.psuinterface.R


class ProfileGroupsRecyclerViewAdapter(private val items: List<ProfileGroupDTO>, private val listener : (Any) -> Unit) :
    RecyclerView.Adapter<ProfileGroupsViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileGroupsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProfileGroupsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProfileGroupsViewHolder, position: Int) {
        val profileGroup: ProfileGroupDTO = items[position]
        holder.bind(profileGroup, listener)
    }

}


class ProfileGroupsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_profile_group, parent, false)) {
    private val TAG = ProfileGroupsViewHolder::class.java.simpleName

    private var mGroupName: TextView? = null
    private var mGroupProfileCount: TextView? = null
    private var mEditButton: ImageButton? = null
    private var mProfileGroupClickableArea: LinearLayout? = null

    init {
        mGroupName = itemView.findViewById(R.id.textView_profile_group_name)
        mGroupProfileCount = itemView.findViewById(R.id.textView_profile_group_count)
        mEditButton = itemView.findViewById(R.id.imageButton_profile_group_edit)
        mProfileGroupClickableArea = itemView.findViewById(R.id.linearLayout_profile_group)
    }

    fun bind(profileGroup: ProfileGroupDTO, listener: (Any) -> Unit) {
        Log.d(TAG, "bind")
        setTextViews(profileGroup)
        mProfileGroupClickableArea?.setOnClickListener { listener(profileGroup.profileGroupName) }

    }

    private fun setTextViews(profileGroup: ProfileGroupDTO) {
        mGroupName?.text = profileGroup.profileGroupName
        mGroupProfileCount?.text = profileGroup.profileCount.toString() + " profiles"
    }
}