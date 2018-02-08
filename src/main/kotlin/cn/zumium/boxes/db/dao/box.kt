package cn.zumium.boxes.db.dao

import cn.zumium.boxes.thrift.BoxStatus
import org.jetbrains.exposed.dao.*

object Boxes : LongIdTable() {
    val name = varchar("name", 256)/*.uniqueIndex()*/
    val description = varchar("description", 512).nullable()
    val status = enumeration("status", BoxStatus::class.java)
    val createdAt = datetime("created_at")
}

class Box(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Box>(Boxes)

    var name by Boxes.name
    var description by Boxes.description
    var status by Boxes.status
    var createdAt by Boxes.createdAt
}