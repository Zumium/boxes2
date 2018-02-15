package cn.zumium.boxes.controller

import cn.zumium.boxes.db.DbManager
import cn.zumium.boxes.fs.FsManager
import cn.zumium.boxes.thrift.Box
import cn.zumium.boxes.thrift.BoxService.Iface
import cn.zumium.boxes.thrift.BoxStatus
import cn.zumium.boxes.util.reportException
import cn.zumium.boxes.util.toThriftBox
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import org.jetbrains.exposed.sql.transactions.transaction

class BoxController(override val kodein: Kodein) : Iface, KodeinAware {
    private val dbManager = instance<DbManager>()
    private val fsManager = instance<FsManager>()

    override fun create(name: String, description: String) {
        reportException("creating box") {
            transaction {
                dbManager.box.create(name, description)
                fsManager.box.create(name)
            }
        }
    }

    override fun remove(id: Long) {
        reportException("removing box") {
            transaction {
                val box = dbManager.box.getBox(id)
                fsManager.box.remove(box.name)
                box.delete()
            }
        }
    }

    override fun setDescription(id: Long, description: String) {
        reportException("setting description of box") {
            transaction {
                val box = dbManager.box.getBox(id)
                box.description = description
            }
        }
    }

    override fun setName(id: Long, name: String) {
        reportException("setting name of box") {
            transaction {
                val box = dbManager.box.getBox(id)
                box.name = name
            }
        }
    }

    override fun archive(id: Long) {
        reportException("archiving box") {
            transaction {
                val box = dbManager.box.getBox(id)
                fsManager.archive.archive(box.name)
                box.status = BoxStatus.ARCHIVED
                fsManager.box.remove(box.name)
            }
        }
    }

    override fun unarchive(id: Long) {
        reportException("unarchiving box") {
            transaction {
                val box = dbManager.box.getBox(id)
                fsManager.archive.unarchive(box.name)
                box.status = BoxStatus.OPEN
                fsManager.archive.remove(box.name)
            }
        }
    }

    override fun currentBoxes(): List<Box> = reportException("querying current boxes") { transaction { dbManager.box.getAllBox().map { it.toThriftBox() } } }

    override fun get(id: Long): Box = reportException("querying box detail") { transaction { dbManager.box.getBox(id).toThriftBox() } }
}