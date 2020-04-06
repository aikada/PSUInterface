package ee.itcollege.aikada.psuinterface.ui.profilegroups

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ee.itcollege.aikada.psuinterface.R

class ProfileGroupsFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileGroupsFragment()
    }

    private lateinit var viewModel: ProfileGroupsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile_groups, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileGroupsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
