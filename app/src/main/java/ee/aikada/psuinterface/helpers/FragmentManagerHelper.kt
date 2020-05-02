package ee.aikada.psuinterface.helpers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ee.aikada.psuinterface.models.DTO.GraphXDTO
import ee.aikada.psuinterface.models.DTO.GraphYDTO
import ee.aikada.psuinterface.ui.profiles.componentFragments.*

class FragmentManagerHelper(
    private val fragmentManagerToUse: FragmentManager,
    private val parentLayoutId: Int
) {

    fun addSetParameterFragment(title: String, value: Float?, unit: String): SetParameterFragment {
        val parameterFragmentInstance =
            SetParameterFragment.newInstance(
                title,
                value ?: 0F,
                unit
            )
        fragmentManagerToUse.beginTransaction()
            .add(parentLayoutId, parameterFragmentInstance).commitNow()
        return parameterFragmentInstance
    }

    fun addSetTimePropertyFragment(title: String, time: String?): SetTimePropertyFragment {
        val timeFragmentInstance =
            SetTimePropertyFragment.newInstance(
                title,
                time ?: "00:00:00"
            )
        fragmentManagerToUse.beginTransaction()
            .add(parentLayoutId, timeFragmentInstance).commitNow()
        return timeFragmentInstance

    }

    fun addSetBooleanFragment(title: String, bool: Boolean?): SetBooleanFragment {
        val timeFragmentInstance =
            SetBooleanFragment.newInstance(
                title,
                bool?: false
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

    fun addSetParameterGraphFragment(title: String, value: Float?, unit: String, graphYDTO: GraphYDTO): SetParameterGraphFragment {
        val parameterFragmentInstance =
            SetParameterGraphFragment.newInstance(
                title,
                value ?: 0F,
                unit,
                graphYDTO
            )
        fragmentManagerToUse.beginTransaction()
            .add(parentLayoutId, parameterFragmentInstance).commitNow()
        return parameterFragmentInstance
    }

    fun addSetParameterGraphXFragment(title: String, value: Float?, unit: String, graphXDTO: GraphXDTO): SetParameterGraphXFragment {
        val parameterFragmentInstance =
            SetParameterGraphXFragment.newInstance(
                title,
                value ?: 0F,
                unit,
                graphXDTO
            )
        fragmentManagerToUse.beginTransaction()
            .add(parentLayoutId, parameterFragmentInstance).commitNow()
        return parameterFragmentInstance
    }
}