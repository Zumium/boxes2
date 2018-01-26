package cn.zumium.boxes.controller

import cn.zumium.boxes.thrift.Box
import cn.zumium.boxes.thrift.BoxService.Iface
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware

class BoxController(override val kodein: Kodein) : Iface, KodeinAware {
    override fun create(name: String?, description: String?) {

    }

    override fun remove(id: Long) {

    }

    override fun setDescription(id: Long, description: String?) {

    }

    override fun setName(id: Long, name: String?) {

    }

    override fun archive(boxId: Long) {

    }

    override fun unarchive(boxId: Long) {

    }

    override fun currentBoxes(): MutableList<Box> {
        return mutableListOf() //TO BE REMOVED
    }

    override fun get(id: Long): Box {
        return Box() //TO BE REMOVED
    }
}