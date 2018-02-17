package cn.zumium.boxes.db

import cn.zumium.boxes.config.ConfigManager
import cn.zumium.boxes.db.dao.Box
import cn.zumium.boxes.db.dao.Boxes
import cn.zumium.boxes.db.dao.Link
import cn.zumium.boxes.db.dao.Links
import cn.zumium.boxes.thrift.BoxStatus
import cn.zumium.boxes.thrift.LinkType
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class DbManager(override val kodein: Kodein) : KodeinAware {
    inner class BoxManager {
        fun create(boxName: String, descrip: String? = null): Box {
            return Box.new {
                name = boxName
                description = descrip ?: ""
                status = BoxStatus.OPEN
                createdAt = DateTime.now()
            }
        }

        fun getBox(id: Long) = Box.findById(id) ?: throw DbBoxNotFoundException(id)
        fun getAllBox() = Box.all().toList()
    }

    inner class LinkManager {
        fun create(srcBox: Box, innerPth: String, dst:String, ltype: LinkType): Link {
            return Link.new {
                box = srcBox
                innerPath = innerPth
                destination = dst
                type = ltype
                createdAt = DateTime.now()
            }
        }

        fun getAllLink() = Link.all()
        fun getLink(id: Long) = Link.findById(id) ?: throw DbLinkNotFoundException(id)
        fun getLink(box: Box) = Link.find { Links.box eq box.id }
        fun getLink(box: Box, innerPath: String) = Link.find { Links.box eq box.id and (Links.innerPath eq innerPath) }
        fun getLink(destination: String) = Link.find { Links.destination eq destination }.first()
    }

    private val configManager = instance<ConfigManager>()

    val box = BoxManager()
    val link = LinkManager()

    fun init() {
        Database.connect("jdbc:h2:${configManager.dbPath()}", driver = "org.h2.Driver")
        transaction {
            SchemaUtils.create(Boxes, Links)
        }
    }
}