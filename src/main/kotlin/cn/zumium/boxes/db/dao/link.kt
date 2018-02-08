package cn.zumium.boxes.db.dao

import cn.zumium.boxes.thrift.LinkType
import org.jetbrains.exposed.dao.*

object Links : LongIdTable() {
    val innerPath = varchar("inner_path", 512)
    val box = reference("box", Boxes)
    val destination = varchar("destination", 512)/*.uniqueIndex()*/
    val type = enumeration("type", LinkType::class.java)
    val createdAt = datetime("created_at")
}

class Link(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Link>(Links)

    var innerPath by Links.innerPath
    var box by Box referencedOn Links.box
    var destination by Links.destination
    var type by Links.type
    var createdAt by Links.createdAt
}