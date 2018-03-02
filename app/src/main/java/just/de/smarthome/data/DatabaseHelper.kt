package just.de.smarthome.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import just.de.smarthome.device.Device
import just.de.smarthome.room.Room

class DatabaseHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table rooms(id int primary key, name text, image int default 0, description text)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun queryForRooms():  List<Room> {
        val rooms: ArrayList<Room> = ArrayList()
        val db = writableDatabase
        val row = db.rawQuery("select name, image from rooms", null)
        if (row.moveToFirst()) {
            do {
                rooms.add(Room(row.getString(0), row.getInt(1), arrayListOf(Device("Foo"))))
                Log.d("DATABASE", row.getString(0))
            } while (row.moveToNext())
        }
        row.close()
        db.close()
        return rooms
    }

}
