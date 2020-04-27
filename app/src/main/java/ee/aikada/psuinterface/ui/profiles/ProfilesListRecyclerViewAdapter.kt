package ee.aikada.psuinterface.ui.profiles

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.DTO.ProfileDTO
import ee.aikada.psuinterface.R


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


    init {
        mProfileName = itemView.findViewById(R.id.textView_profile_name)
    }

    fun bind(profile: ProfileDTO, listener: (Any) -> Unit) {
        Log.d(TAG, "bind")
        setTextViews(profile)
        itemView.setOnClickListener { listener(profile) }
    }

    private fun setTextViews(profile: ProfileDTO) {
        mProfileName?.text = profile.profileName

    }
}