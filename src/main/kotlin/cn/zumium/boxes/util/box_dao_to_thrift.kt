package cn.zumium.boxes.util

import cn.zumium.boxes.db.dao.Box

fun Box.toThriftBox() = cn.zumium.boxes.thrift.Box(id.value, name, description, createdAt.toString())