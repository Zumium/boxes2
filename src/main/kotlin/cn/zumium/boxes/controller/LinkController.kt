package cn.zumium.boxes.controller

import cn.zumium.boxes.db.DbManager
import cn.zumium.boxes.fs.FsManager
import cn.zumium.boxes.thrift.Link
import cn.zumium.boxes.thrift.LinkService.Iface
import cn.zumium.boxes.thrift.LinkType
import cn.zumium.boxes.util.reportException
import cn.zumium.boxes.util.toThriftLink
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import org.jetbrains.exposed.sql.transactions.transaction


class LinkController(override val kodein: Kodein) : Iface, KodeinAware {
    private val dbManager = instance<DbManager>()
    private val fsManager = instance<FsManager>()

    override fun create(boxId: Long, innerPath: String, destination: String, linkType: LinkType) {
        reportException("creating link") {
            transaction {
                val box = dbManager.box.getBox(boxId)
                dbManager.link.create(box, innerPath, destination, linkType)
                fsManager.link.create(box.name, innerPath, destination, linkType)
            }
        }
    }

    override fun lsAll(): List<Link> = reportException("listing all links") { transaction { dbManager.link.getAllLink().map { it.toThriftLink() }.toList() } }

    override fun lsBox(boxId: Long): List<Link> = reportException("listing links of a box") {
        transaction {
            val box = dbManager.box.getBox(boxId)
            dbManager.link.getLink(box).map { it.toThriftLink() }
        }
    }

    override fun lsInner(boxId: Long, innerPath: String): Link = reportException("removing link") {
        transaction {
            val box = dbManager.box.getBox(boxId)
            dbManager.link.getLink(box, innerPath).toThriftLink()
        }
    }

    override fun removeAll() {
        reportException("removing all links") {
            transaction {
                dbManager.link.getAllLink().forEach {
                    fsManager.link.remove(it.destination)
                    it.delete()
                }
            }
        }
    }

    override fun removeByBox(boxId: Long) {
        reportException("removing links of the box") {
            transaction {
                val box = dbManager.box.getBox(boxId)
                dbManager.link.getLink(box).forEach {
                    fsManager.link.remove(it.destination)
                    it.delete()
                }
            }
        }
    }

    override fun removeByDestination(destination: String) {
        reportException("removing link at destination") {
            transaction {
                dbManager.link.getLink(destination).delete()
                fsManager.link.remove(destination)
            }
        }
    }

    override fun removeById(id: Long) {
        reportException("removing link by id") {
            transaction {
                val link = dbManager.link.getLink(id)
                fsManager.link.remove(link.destination)
                link.delete()
            }
        }
    }

    override fun removeByInner(boxId: Long, innerPath: String) {
        reportException("removing link") {
            transaction {
                val box = dbManager.box.getBox(boxId)
                val link = dbManager.link.getLink(box, innerPath)
                fsManager.link.remove(link.destination)
                link.delete()
            }
        }
    }
}