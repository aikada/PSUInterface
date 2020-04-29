package ee.aikada.psuinterface.ui.profiles.componentFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.DTO.GraphYDTO
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.ui.profiles.ProfileAddEditViewModel

private const val ARG_TITLE = "param1"
private const val ARG_VALUE = "param2"
private const val ARG_UNIT = "param3"
private const val ARG_POINTS = "param4"


class SetParameterGraphFragment : Fragment() {
    val TAG = SetParameterGraphFragment::class.java.simpleName

    private var title: String? = null
    private var value: Float? = null
    private var unit: String? = null
    private var graphYDTO: GraphYDTO? = null
    private var mEditTextValue: EditText? = null
    private var recyclerAdapter: GraphPointRecyclerViewAdapter? = null
    private var recycler: RecyclerView? = null
    private lateinit var viewModel: ProfileAddEditViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            value = it.getFloat(ARG_VALUE)
            unit = it.getString(ARG_UNIT)
            graphYDTO = it.getParcelable(ARG_POINTS)
        }
        viewModel = ViewModelProvider(requireActivity()).get(ProfileAddEditViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "value for field " + value.toString())
        val v = inflater.inflate(R.layout.fragment_set_parameter_graph, container, false)
        mEditTextValue = v.findViewById<EditText>(R.id.fragment_set_parameter_graph_value)

        mEditTextValue!!.setText(value.toString())
        v.findViewById<TextView>(R.id.fragment_set_parameter_graph_name).text = title
        v.findViewById<TextView>(R.id.fragment_set_parameter_graph_unit).text = unit
        v.findViewById<TextView>(R.id.fragment_set_parameter_graph_button_add)
            .setOnClickListener { Log.d(TAG, "CLICKED")
                addPoint()}
        return v
    }

    fun addPoint() {
        val x = viewModel.getGraphX()!!.value.toString()
        val y = mEditTextValue!!.text.toString()

        val point = arrayOf(x.toFloat(), y.toFloat())
        graphYDTO!!.points.add(point)
        setUpRecyclerView(view!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(view)
    }

    private fun setUpRecyclerView(view: View) {
        if (graphYDTO == null) {
            return
        }
        recyclerAdapter =
            GraphPointRecyclerViewAdapter(graphYDTO!!.points) {
                    position -> removePoint(position as Int)
            }

        recycler = view.findViewById<RecyclerView>(R.id.recycler_graph_points).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerAdapter
        }
    }

    private fun removePoint(position: Int) {
        // https://stackoverflow.com/questions/31367599/how-to-update-recyclerview-adapter-data
        graphYDTO!!.points.removeAt(position)
        recycler!!.removeViewAt(position);
        recyclerAdapter!!.notifyItemRemoved(position)
        recyclerAdapter!!.notifyItemRangeChanged(position, graphYDTO!!.points.size)
        recyclerAdapter!!.notifyDataSetChanged();
    }

    companion object {
        fun newInstance(
            title: String,
            value: Float,
            unit: String,
            graphYDTO: GraphYDTO
        ) =
            SetParameterGraphFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putFloat(ARG_VALUE, value)
                    putString(ARG_UNIT, unit)
                    putParcelable(ARG_POINTS, graphYDTO)
                }
            }
    }
}
