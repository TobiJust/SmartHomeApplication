package just.de.smarthome.room

import just.de.smarthome.device.Device

data class Room(val name: String?, val image: Int, val devices: ArrayList<Device>)
