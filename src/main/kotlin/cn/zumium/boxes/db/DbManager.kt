package cn.zumium.boxes.db

import cn.zumium.boxes.db.dao.Box
import cn.zumium.boxes.db.dao.Boxes
import cn.zumium.boxes.db.dao.Links
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.joda.time.DateTime

class DbManager(override val kodein: Kodein) : KodeinAware {
    class BoxManager {
        fun create(boxName: String, descrip: String? = null): Box {
            return Box.new {
                name = boxName
                description = descrip ?: ""
                createdAt = DateTime.now()
            }
        }

        fun getBox(boxName: String) = Box.find { Boxes.name eq boxName }.first()
        fun getBox(id: Long) = Box.findById(id)
    }
    class LinkManager {

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