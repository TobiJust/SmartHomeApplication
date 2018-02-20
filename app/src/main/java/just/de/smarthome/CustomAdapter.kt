package just.de.smarthome

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.room_item_view.view.*

class CustomAdapter(private val context : Context, val list : ArrayList<Room>, private val devicesAdapter: MyItemRecyclerViewAdapter) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbImageView: ImageView = itemView.findViewById(R.id.thumbnail)
    }
    override fun onCreateViewHolder(parent : ViewGroup, type : Int) : CustomAdapter.ViewHolder{
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.room_item_view, parent, false)
        val card = view.findViewById<CardView>(R.id.card_view)
        card.maxCardElevation = 2.0F
        card.radius = 5.0F

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder : CustomAdapter.ViewHolder, position : Int){
        val room : Room = list[position]
        holder.thumbImageView.setImageResource(room.image)
        holder.itemView.card_view.setOnClickListener {
            if(position == list.size - 1) {
                addRoom(Room("Foo", R.drawable.ic_dashboard_black_24dp, ArrayList()))
            }
            devicesAdapter.mValues = room.devices
            devicesAdapter.notifyDataSetChanged()
        }
    }

    private fun addRoom(room : Room) {
        list.add(list.size-1, room)
        notifyDataSetChanged()
    }

    override fun getItemCount() : Int{
        return list.size
    }
}