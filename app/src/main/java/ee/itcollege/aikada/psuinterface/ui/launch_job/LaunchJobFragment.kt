package ee.itcollege.aikada.psuinterface.ui.launch_job

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ee.itcollege.aikada.psuinterface.R

class LaunchJobFragment : Fragment() {

    companion object {
        fun newInstance() = LaunchJobFragment()
    }

    private lateinit var viewModel: LaunchJobViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_job, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LaunchJobViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
