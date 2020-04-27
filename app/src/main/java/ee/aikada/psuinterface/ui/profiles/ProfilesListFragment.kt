package ee.aikada.psuinterface.ui.profiles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.DTO.ProfileDTO
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.controllers.ProfileController


class ProfilesListFragment(groupName: String? = null) : Fragment() {

    private lateinit var viewModel: ProfilesViewModel
    val TAG = ProfilesListFragment::class.java.simpleName
    private var groupName: String? = null
    private var recyclerAdapter: ProfilesRecyclerViewAdapter? = null
    var profiles: List<ProfileDTO> = listOf()

    init {
        this.groupName = groupName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = groupName

        return inflater.inflate(R.layout.fragment_profiles_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profileController =
            ProfileController(activity!!.applicationContext)
        if (groupName != null) {
            profiles = profileController.getProfilesForGroup(groupName!!)
            setUpRecyclerView(view)
        }
    }

    private fun setUpRecyclerView(view: View) {
        Log.d(TAG, "setUpRecyclerView: " + profiles)

        recyclerAdapter = ProfilesRecyclerViewAdapter(profiles) {
                profile -> openProfile(profile as ProfileDTO)
        }

        view.findViewById<RecyclerView>(R.id.recycler_profiles).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfilesViewModel::class.java)
    }

    private fun openProfile(profile: ProfileDTO? = null) {
        Log.d(TAG, "openProfile: " + profile)
        val profileAddEditFragment = ProfileAddEditFragment.newInstance(profile)
        activity!!.supportFragmentManager.beginTransaction()
            .replace(this.id, profileAddEditFragment)
            .addToBackStack(profileAddEditFragment.TAG)
            .commit()
    }

    companion object {
        fun newInstance(groupName: String?) = ProfilesListFragment(groupName)
        fun newInstance() = ProfilesListFragment()

    }

}
