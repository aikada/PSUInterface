package ee.aikada.psuinterface.ui.profiles

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.models.DTO.ProfileDTO


class ProfilesRecyclerViewAdapter(private val items: List<ProfileDTO>, private val listener : (Any) -> Unit) :
    RecyclerView.Adapter<ProfileGroupsViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileGroupsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProfileGroupsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProfileGroupsViewHolder, position: Int) {
        val profile: ProfileDTO = items[position]
        holder.bind(profile, listener)
    }
}


class ProfileGroupsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_profile, parent, false)) {
    private val TAG = ProfileGroupsViewHolder::class.java.simpleName

    private var mProfileName: TextView? = null
    private var mProfileType: TextView? = null
    private var mProfileField1: TextView? = null
    private var mProfileField2: TextView? = null
    private var mProfileField3: TextView? = null
    private var mProfileField4: TextView? = null
    private var mProfileTime1: TextView? = null
    private var mProfileTime2: TextView? = null


    init {
        mProfileName = itemView.findViewById(R.id.textView_profile_name)
        mProfileType = itemView.findViewById(R.id.textView_profile_type)
        mProfileField1 = itemView.findViewById(R.id.textView_profile_field1)
        mProfileField2 = itemView.findViewById(R.id.textView_profile_field2)
        mProfileField3 = itemView.findViewById(R.id.textView_profile_field3)
        mProfileField4 = itemView.findViewById(R.id.textView_profile_field4)
        mProfileTime1 = itemView.findViewById(R.id.textView_profile_time_1)
        mProfileTime2 = itemView.findViewById(R.id.textView_profile_time_2)
    }

    fun bind(profile: ProfileDTO, listener: (Any) -> Unit) {
        Log.d(TAG, "bind")
        setTextViews(profile)
        itemView.setOnClickListener { listener(profile) }
    }

    private fun setTextViews(profile: ProfileDTO) {
        val currentProfile = profile.profileType

        mProfileName?.text = profile.profileName
        mProfileType?.text = profile.profileType.profileTypeName
        mProfileField1?.text = currentProfile.formatField1(profile)
        mProfileField2?.text = currentProfile.formatField2(profile)
        mProfileField3?.text = currentProfile.formatField3(profile)
        mProfileField4?.text = currentProfile.formatField4(profile)
        mProfileTime1?.text = currentProfile.formatTimeField1(profile)
        mProfileTime2?.text = currentProfile.formatTimeField2(profile)
    }
}