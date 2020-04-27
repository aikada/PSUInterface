package ee.aikada.psuinterface.ui.profiles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ee.aikada.psuinterface.ProfileActivity
import ee.aikada.psuinterface.R
import kotlinx.android.synthetic.main.fragment_profiles_startpage.*

class ProfilesStartFragment : Fragment() {
    private val TAG = ProfilesStartFragment::class.java.simpleName
    private lateinit var viewModel: ProfileAddEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = "Profiles"
        return inflater.inflate(R.layout.fragment_profiles_startpage, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileAddEditViewModel::class.java)
        button_profile_start_browse.setOnClickListener { openProfileActivity() }
        button_profile_start_new.setOnClickListener { newProfile() }


        // TODO: Use the ViewModel
    }

    fun openProfileActivity() {
        Log.d(TAG, "openProfileActivity")
        val intent = Intent(activity, ProfileActivity::class.java)
        activity?.startActivity(intent)
    }

    fun newProfile() {
        Log.d(TAG, "newProfile")
        val profileAddEditFragment = ProfileAddEditFragment.newInstance()
        activity!!.supportFragmentManager.beginTransaction()
            .add(this.id, profileAddEditFragment)
            .addToBackStack(profileAddEditFragment.TAG)
            .commit()
    }

    companion object {
        fun newInstance() = ProfilesStartFragment()
    }

}
