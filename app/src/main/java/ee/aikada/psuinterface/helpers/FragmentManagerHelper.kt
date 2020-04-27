package ee.aikada.psuinterface.helpers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ee.aikada.psuinterface.ui.profiles.componentFragments.SetBooleanFragment
import ee.aikada.psuinterface.ui.profiles.componentFragments.SetParameterFragment
import ee.aikada.psuinterface.ui.profiles.componentFragments.SetTimePropertyFragment
import ee.aikada.psuinterface.ui.profiles.profileTypeFragments.CCFragment

class FragmentManagerHelper(
    private val fragmentManagerToUse: FragmentManager,
    private val parentLayoutId: Int
) {

    fun addSetParameterFragment(title: String, value: Float, unit: String): SetParameterFragment {
        val parameterFragmentInstance =
            SetParameterFragment.newInstance(
                title,
                value,
                unit
            )
        fragmentManagerToUse.beginTransaction()
            .add(parentLayoutId, parameterFragmentInstance).commitNow()
        return parameterFragmentInstance
    }

    fun addSetTimePropertyFragment(title: String, time: String): SetTimePropertyFragment {
        val timeFragmentInstance =
            SetTimePropertyFragment.newInstance(
                title,
                time
            )
        fragmentManagerToUse.beginTransaction()
            .add(parentLayoutId, timeFragmentInstance).commitNow()
        return timeFragmentInstance

    }

    fun addSetBooleanFragment(title: String, bool: Boolean): SetBooleanFragment {
        val timeFragmentInstance =
            SetBooleanFragment.newInstance(
                title,
                bool
            )
        fragmentManagerToUse.beginTransaction()
            .add(parentLayoutId, timeFragmentInstance).commitNow()
        return timeFragmentInstance

    }

    fun replaceCurrentFragmentWith(fragment: Fragment) {
        fragmentManagerToUse.beginTransaction()
            .replace(
                parentLayoutId,
                fragment
            )
            .commitNow()
    }
}