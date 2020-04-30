package ee.aikada.psuinterface.ui.profileGroups

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.DTO.ProfileGroupDTO
import ee.aikada.psuinterface.ProfileActivity
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.controllers.ProfileController
import ee.aikada.psuinterface.ui.profiles.ProfilesListFragment

class ProfileGroupsFragment : Fragment() {
    val TAG = ProfileGroupsFragment::class.java.simpleName
    private var recyclerAdapter: ProfileGroupsRecyclerViewAdapter? = null

    var profileGroups: List<ProfileGroupDTO> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileController = ProfileController(activity!!.applicationContext)


        profileGroups = profileController.getProfileGroups()

        return inflater.inflate(R.layout.fragment_profiles_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(view)
        activity?.title = "Profile groups"

    }

    private fun setUpRecyclerView(view: View) {
        recyclerAdapter = ProfileGroupsRecyclerViewAdapter(profileGroups) { groupName ->
            openProfileGroup(groupName.toString())
        }
        view.findViewById<RecyclerView>(R.id.recycler_profile_groups).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerAdapter
        }
    }

    private fun openProfileGroup(groupName: String) {
        Log.d(TAG, "openProfileGroup " + groupName + activity)

        if (activity is ProfileActivity) {
            val profilesFragment = ProfilesListFragment.newInstance(groupName)
            activity!!.supportFragmentManager.beginTransaction()
                .replace(this.id, profilesFragment)
                .addToBackStack(profilesFragment.TAG)
                .commit()
        } else {
            openProfileActivity(groupName)
        }


    }

    fun openProfileActivity(groupName: String) {
        Log.d(TAG, "openProfileActivity: " + groupName)
        val intent = Intent(activity, ProfileActivity::class.java)
        intent.putExtra("groupName", groupName)
        activity?.startActivity(intent)
    }

    companion object {
        fun newInstance() = ProfileGroupsFragment()
    }

}
