package ee.aikada.psuinterface.ui.profiles.profileTypeFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ee.aikada.psuinterface.DTO.GraphDTO
import ee.aikada.psuinterface.DTO.GraphXDTO
import ee.aikada.psuinterface.DTO.GraphYDTO
import ee.aikada.psuinterface.DTO.ProfileDTO
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.helpers.FragmentManagerHelper
import ee.aikada.psuinterface.helpers.Util
import ee.aikada.psuinterface.models.enums.GraphType
import ee.aikada.psuinterface.ui.profiles.ProfileAddEditViewModel

class GraphFragment(val profile: ProfileDTO? = null) : Fragment() {
    val TAG = GraphFragment::class.java.simpleName
    private lateinit var viewModel: ProfileAddEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(ProfileAddEditViewModel::class.java)
        val v = inflater.inflate(R.layout.fragment_profile_type_container, container, false)
        if (profile?.graph ==  null) {
            Util.initGraphPropertyForProfile(profile!!)
        }
        addFragmentsForProfile()

        return v
    }

    private fun addFragmentsForProfile() {
        val fragmentManagerHelper =
            FragmentManagerHelper(
                childFragmentManager,
                R.id.linearLayout_fragment_profile_type_container
            )

        val x = profile!!.graph!!.x
        fragmentManagerHelper.addSetParameterGraphXFragment(
            x.value!!.displayName,
            0.0F,
            x.value!!.unit,
            x
        )
        for (y in profile.graph!!.y) {
            fragmentManagerHelper.addSetParameterGraphFragment(
                y.value!!.displayName,
                0.0F,
                y.value!!.unit,
                y
            )
        }
        fragmentManagerHelper.addSetBooleanFragment("Disable output when limit exceeded", false)
    }


    companion object {
        fun newInstance() = GraphFragment()
        fun newInstance(profile: ProfileDTO?) =
            GraphFragment(profile)
    }
}

