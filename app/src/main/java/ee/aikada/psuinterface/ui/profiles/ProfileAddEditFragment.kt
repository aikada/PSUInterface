package ee.aikada.psuinterface.ui.profiles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ee.aikada.psuinterface.DTO.ProfileDTO
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.helpers.FragmentManagerHelper
import ee.aikada.psuinterface.models.enums.ProfileType
import ee.aikada.psuinterface.ui.profiles.profileTypeFragments.CCFragment
import ee.aikada.psuinterface.ui.profiles.profileTypeFragments.CVFragment
import ee.aikada.psuinterface.ui.profiles.profileTypeFragments.GraphFragment


class ProfileAddEditFragment(var profile: ProfileDTO? = null) : Fragment() {
    val TAG = ProfileAddEditFragment::class.java.simpleName
    private lateinit var viewModel: ProfileAddEditViewModel

    // https://www.youtube.com/watch?v=D5l7MNlqA3M
    private lateinit var mSpinnerProfileType: Spinner
    private lateinit var mSelectedProfileType: ProfileType

    companion object {
        fun newInstance() = ProfileAddEditFragment()
        fun newInstance(profile: ProfileDTO?) = ProfileAddEditFragment(profile)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (profile == null) profile = ProfileDTO()

        return inflater.inflate(R.layout.fragment_profile_add_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProfileAddEditViewModel::class.java)
        viewModel.profile = profile

        activity?.title = if (profile != null) profile!!.profileName else "New profile"
        setupProfileTypeSpinner(view)
    }

    private fun setupProfileTypeSpinner(view: View) {
        mSpinnerProfileType = view.findViewById(R.id.spinner_profile_add_edit)
        mSpinnerProfileType.adapter =
            ArrayAdapter(context!!, android.R.layout.simple_list_item_1, ProfileType.values())
        mSpinnerProfileType.setSelection(profile!!.profileType.ordinal)
        mSpinnerProfileType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                mSelectedProfileType = if (profile != null) profile!!.profileType else ProfileType.CV
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mSelectedProfileType = mSpinnerProfileType.adapter.getItem(position) as ProfileType
                Log.d(TAG, "onItemSelected: " + mSelectedProfileType)
                setProfileTypeFragment()
            }
        }
    }

    private fun setProfileTypeFragment() {
        val fragmentManagerHelper = FragmentManagerHelper(childFragmentManager, R.id.frameLayout_profileType_container)
        when (mSelectedProfileType) {
            ProfileType.CV -> {
                fragmentManagerHelper.replaceCurrentFragmentWith(CVFragment.newInstance(profile))
            }
            ProfileType.CC -> {
                fragmentManagerHelper.replaceCurrentFragmentWith(CCFragment.newInstance(profile))
            }
            ProfileType.Graph -> {
                fragmentManagerHelper.replaceCurrentFragmentWith(GraphFragment.newInstance(profile))
            }
        }
    }
}
