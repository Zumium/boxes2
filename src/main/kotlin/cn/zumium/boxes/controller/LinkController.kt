package cn.zumium.boxes.controller

import cn.zumium.boxes.thrift.Link
import cn.zumium.boxes.thrift.LinkService.Iface
import cn.zumium.boxes.thrift.LinkType
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware


class LinkController(override val kodein: Kodein) : Iface, KodeinAware {
    override fun create(boxId: Long, innerPath: String?, destination: String?, linkType: LinkType?) {

    }

    override fun lsAll(): MutableList<Link> {
        return mutableListOf<Link>() //TO BE REMOVED
    }

    override fun lsBox(boxId: Long): MutableList<Link> {
        return mutableListOf<Link>() //TO BE REMOVED
    }

    override fun lsInner(boxId: Long, innerPath: String?): MutableList<Link> {
        return mutableListOf<Link>() //TO BE REMOVED
    }

    override fun removeAll() {

    }

    override fun removeByBox(boxId: Long) {

    }

    override fun removeByDestination(destination: String?) {

    }

    override fun removeById(id: Long) {

    }

    override fun removeByInner(boxId: Long, innerPath: String?) {

    }
}