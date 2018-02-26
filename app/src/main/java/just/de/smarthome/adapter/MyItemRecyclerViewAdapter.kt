package just.de.smarthome.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import just.de.smarthome.device.Device
import just.de.smarthome.screen.main.DevicesFragment.OnListFragmentInteractionListener
import just.de.smarthome.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MyItemRecyclerViewAdapter(var mValues: List<Device>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mIdView.text = mValues[position].name
        holder.onOffButton.setOnCheckedChangeListener {
            buttonView, isChecked ->
            when (isChecked) {
                true -> {
                    Log.d("OnOff", "Checked")
                    Toast.makeText(buttonView.context, "Checked", Toast.LENGTH_SHORT).show()
                }
                false -> Log.d("OnOff", "Not checked")
            }
        }

        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    private fun turnOn(){
        doAsync {
            val result = URL("<api call>").readText()
            uiThread {
                Log.d("Request", result)
            }
        }
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.findViewById<View>(R.id.deviceName) as TextView
        val onOffButton: ToggleButton = mView.findViewById<View>(R.id.toggleButton) as ToggleButton
        var mItem: Device? = null

        override fun toString(): String {
            return super.toString() + " '"
        }
    }
}
