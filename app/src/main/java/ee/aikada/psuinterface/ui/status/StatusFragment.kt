package ee.aikada.psuinterface.ui.status

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.ProfileActivity
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.models.StatusItem

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StatusFragment : Fragment() {

    val statusItems: List<StatusItem> = listOf(
        StatusItem("CH1"),
        StatusItem("CH2")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFirstButton(view)
        setUpRecyclerView(view)
    }

    private fun setUpRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.recycler_status_items).apply {
            // set a LinearLayoutManager to handle Android RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = StatusRecyclerViewAdapter(statusItems)
        }
    }

    private fun setUpFirstButton(view: View) {
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): StatusFragment = StatusFragment()
    }

}
