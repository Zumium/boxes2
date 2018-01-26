package cn.zumium.boxes.db

import cn.zumium.boxes.db.dao.Box
import cn.zumium.boxes.db.dao.Boxes
import cn.zumium.boxes.db.dao.Link
import cn.zumium.boxes.db.dao.Links
import cn.zumium.boxes.thrift.BoxStatus
import cn.zumium.boxes.thrift.LinkType
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.joda.time.DateTime

class DbManager(override val kodein: Kodein) : KodeinAware {
    class BoxManager {
        fun create(boxName: String, descrip: String? = null): Box {
            return Box.new {
                name = boxName
                description = descrip ?: ""
                status = BoxStatus.OPEN
                createdAt = DateTime.now()
            }
        }

        fun getBox(id: Long) = Box.findById(id)
        fun getAllBox() = Box.all()
    }
    class LinkManager {
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
        fun getLink(id: Long) = Link.findById(id)
        fun getLink(box: Box) = Link.find { Links.box eq box }.first()
        fun getLink(box: Box, innerPath: String) = Link.find { Links.box eq box and (Links.innerPath eq innerPath) }.first()
        fun getLink(destination: String) = Link.find { Links.destination eq destination }.first()
    }

    private val boxManager = BoxManager()
    private val linkManager = LinkManager()

    fun box() = boxManager

    fun link() = linkManager

    fun init() {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
        SchemaUtils.create(Boxes, Links)
    }
}