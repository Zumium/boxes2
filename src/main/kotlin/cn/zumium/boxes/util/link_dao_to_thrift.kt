package cn.zumium.boxes.util

import cn.zumium.boxes.db.dao.Link

fun Link.toThriftLink() = cn.zumium.boxes.thrift.Link(box.id.value, innerPath, destination, type)
