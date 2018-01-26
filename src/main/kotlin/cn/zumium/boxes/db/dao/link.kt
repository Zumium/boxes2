package cn.zumium.boxes.db.dao

import org.jetbrains.exposed.dao.*

object Links : LongIdTable() {
    val file = varchar("file", 512)
    val box = reference("box", Boxes)
    val destination = varchar("destination", 512)
    val createdAt = datetime("created_at")
}

class Link(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Link>(Links)

    val file by Links.file
    val box by Box referencedOn Links.box
    val destination by Links.destination
    val createdAt by Links.createdAt
}