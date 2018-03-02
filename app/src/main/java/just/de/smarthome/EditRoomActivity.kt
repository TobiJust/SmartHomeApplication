package just.de.smarthome

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import just.de.smarthome.adapter.RoomAdapter
import just.de.smarthome.adapter.RoomIconAdapter
import just.de.smarthome.data.DatabaseHelper
import just.de.smarthome.room.Room
import just.de.smarthome.room.RoomIcon
import just.de.smarthome.screen.main.HomeActivity
import kotlinx.android.synthetic.main.content_edit_room.*

import kotlinx.android.synthetic.main.edit_room.*

class EditRoomActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, EditRoomActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_done -> {

                val dbHelper = DatabaseHelper(this, "admin", null, 1)
                val db = dbHelper.writableDatabase
                val register = ContentValues()
                register.put("name", roomName.text.toString())
                //register.put("image", roomIcons.adapter.getItem())
                db.insert("rooms", null, register)
                db.close()
                val intent = HomeActivity.newIntent(this)
                startActivity(intent)
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_room)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val roomIconList = ArrayList<RoomIcon>()
        roomIconList.add(RoomIcon(R.drawable.ic_home_black_24dp))
        roomIconList.add(RoomIcon(R.drawable.ic_dashboard_black_24dp))
        roomIconList.add(RoomIcon(R.drawable.ic_done_white_24dp))
        roomIconList.add(RoomIcon(R.drawable.ic_lightbulb_outline_black_24dp))

        roomIcons.adapter = RoomIconAdapter(this, roomIconList)
    }

}
