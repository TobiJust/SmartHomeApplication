package just.de.smarthome.screen.main

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import just.de.smarthome.R
import just.de.smarthome.adapter.MyItemRecyclerViewAdapter
import just.de.smarthome.adapter.RoomAdapter
import just.de.smarthome.data.DatabaseHelper
import just.de.smarthome.room.Room
import kotlinx.android.synthetic.main.fragment_rooms.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RoomsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RoomsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomsFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var roomName: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    private lateinit var admin: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            roomName = arguments.getString(ROOM_NAME)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
        admin = DatabaseHelper(this.context, "admin", null, 1)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_rooms, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val devicesAdapter = MyItemRecyclerViewAdapter(emptyList(), null)
        devicesView.adapter = devicesAdapter
        devicesView.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)

        val list = ArrayList<Room>()
        prepareList(list)
        val adapter = RoomAdapter(context, list, devicesAdapter)
        adapter.list.add(Room("Add", R.drawable.ic_add_black_24dp, arrayListOf()))
        roomView.adapter = adapter
        roomView.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)

       // if (devicesAdapter.mValues.isEmpty()) {
        //     devicesView.visibility = View.GONE
        //      emptyView.visibility = View.VISIBLE
        //  }
        //  else {
        //       devicesView.visibility = View.VISIBLE
        //       emptyView.visibility = View.GONE
        //   }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        when (context) {
            is OnFragmentInteractionListener -> mListener = context
            else -> throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ROOM_NAME = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RoomsFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(roomName: String?, param2: String?): RoomsFragment {
            val fragment = RoomsFragment()
            val args = Bundle()
            args.putString(ROOM_NAME, roomName)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    private fun prepareList(list: ArrayList<Room>) {
        val rooms = admin.queryForRooms()
        list.addAll(rooms)
    }

}// Required empty public constructor
