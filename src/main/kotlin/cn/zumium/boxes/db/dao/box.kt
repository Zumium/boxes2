package cn.zumium.boxes.db.dao

import org.jetbrains.exposed.dao.*

object Boxes : LongIdTable() {
    val name = varchar("name", 256).uniqueIndex()
    val description = varchar("description", 512).nullable()
    val createdAt = datetime("created_at")
}

class Box(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Box>(Boxes)

    var name by Boxes.name
    var description by Boxes.description
    var createdAt by Boxes.createdAt
}