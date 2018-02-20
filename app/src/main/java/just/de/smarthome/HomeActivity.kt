package just.de.smarthome

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class HomeActivity : AppCompatActivity(), RoomsFragment.OnFragmentInteractionListener, DevicesFragment.OnListFragmentInteractionListener {

    private var content: FrameLayout? = null

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

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        val fragment = RoomsFragment.Companion.newInstance("","")
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment, "TAG")
                .commitAllowingStateLoss()
    }

    override fun onFragmentInteraction(uri: Uri) {
        Toast.makeText(this,"Fragment change", Toast.LENGTH_SHORT).show()
    }

    override fun onListFragmentInteraction(item: Device) {
    }
}
