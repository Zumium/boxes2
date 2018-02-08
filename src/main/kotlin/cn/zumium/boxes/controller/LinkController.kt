package cn.zumium.boxes.controller

import cn.zumium.boxes.db.DbManager
import cn.zumium.boxes.fs.FsManager
import cn.zumium.boxes.thrift.Link
import cn.zumium.boxes.thrift.LinkService.Iface
import cn.zumium.boxes.thrift.LinkType
import cn.zumium.boxes.util.toThriftLink
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import org.jetbrains.exposed.sql.transactions.transaction


class LinkController(override val kodein: Kodein) : Iface, KodeinAware {
    private val dbManager = instance<DbManager>()
    private val fsManager = instance<FsManager>()

    override fun create(boxId: Long, innerPath: String, destination: String, linkType: LinkType) {
        transaction {
            val box = dbManager.box.getBox(boxId)
            dbManager.link.create(box, innerPath, destination, linkType)
            fsManager.link.create(box.name, innerPath, destination, linkType)
        }
    }

    override fun lsAll(): List<Link> = transaction { dbManager.link.getAllLink().map { it.toThriftLink() }.toList() }

    override fun lsBox(boxId: Long): List<Link> {
        val box = dbManager.box.getBox(boxId)
        return dbManager.link.getLink(box).map { it.toThriftLink() }
    }

    override fun lsInner(boxId: Long, innerPath: String): Link = transaction {
        val box = dbManager.box.getBox(boxId)
        dbManager.link.getLink(box, innerPath).toThriftLink()
    }

    override fun removeAll() {
        transaction {
            dbManager.link.getAllLink().forEach {
                fsManager.link.remove(it.destination)
                it.delete()
            }
        }
    }

    override fun removeByBox(boxId: Long) {
        transaction {
            val box = dbManager.box.getBox(boxId)
            dbManager.link.getLink(box).forEach {
                fsManager.link.remove(it.destination)
                it.delete()
            }
        }
    }

    override fun removeByDestination(destination: String) {
        transaction {
            dbManager.link.getLink(destination).delete()
            fsManager.link.remove(destination)
        }
    }

    override fun removeById(id: Long) {
        transaction {
            val link = dbManager.link.getLink(id)
            fsManager.link.remove(link.destination)
            link.delete()
        }
    }

    override fun removeByInner(boxId: Long, innerPath: String) {
        transaction {
            val box = dbManager.box.getBox(boxId)
            val link = dbManager.link.getLink(box, innerPath)
            fsManager.link.remove(link.destination)
            link.delete()
        }
    }
}