package just.de.smarthome.screen.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import just.de.smarthome.R
import just.de.smarthome.device.Device
import just.de.smarthome.room.Room
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File

class HomeActivity : AppCompatActivity(), RoomsFragment.OnFragmentInteractionListener, DevicesFragment.OnListFragmentInteractionListener {

    var file:File? = null

    companion object {

        val FILE_NAME = "just.de.smarthome.data"
        private const val ROOM_NAME = "room_name"


        fun newIntent(context: Context, room: Room): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(HomeActivity.ROOM_NAME, room.name)
            return intent
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                val frag = RoomsFragment.newInstance("", "")
                addFragment(frag)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_rooms -> {
                val frag = RoomsFragment.newInstance("", "")
                addFragment(frag)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_devices -> {
                val frag = DevicesFragment.newInstance(2)
                addFragment(frag)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        file = File(this.filesDir, FILE_NAME)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val roomName = intent.getStringExtra(HomeActivity.ROOM_NAME)
        val fragment = RoomsFragment.Companion.newInstance(roomName, "")
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment, "TAG")
                .commitAllowingStateLoss()
    }

    override fun onFragmentInteraction(uri: Uri) {
        Toast.makeText(this, "Fragment change", Toast.LENGTH_SHORT).show()
    }

    override fun onListFragmentInteraction(item: Device) {
    }
}
