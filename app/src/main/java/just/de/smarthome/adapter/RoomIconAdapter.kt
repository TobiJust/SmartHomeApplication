package just.de.smarthome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import just.de.smarthome.R
import just.de.smarthome.room.RoomIcon
import kotlinx.android.synthetic.main.room_icon_item.view.*


class RoomIconAdapter(private val context : Context, private val list : ArrayList<RoomIcon>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val roomIcon = this.list[position]

        val view : View = LayoutInflater.from(context).inflate(R.layout.room_icon_item, parent, false)
        view.icon.setImageResource(roomIcon.image)

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }


}
