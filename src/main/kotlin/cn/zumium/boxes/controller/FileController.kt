package cn.zumium.boxes.controller

import cn.zumium.boxes.thrift.AddBy
import cn.zumium.boxes.thrift.FetchBy
import cn.zumium.boxes.thrift.FileService.Iface
import cn.zumium.boxes.thrift.LsItem
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware

class FileController(override val kodein: Kodein) : Iface, KodeinAware {
    override fun add(boxId: Long, innerPath: String?, outerPath: String?, addBy: AddBy?) {

    }

    override fun fetch(boxId: Long, innerPath: String?, outerPath: String?, fetchBy: FetchBy?) {

    }

    override fun remove(boxId: Long, innerPath: String?) {

    }

    override fun ls(boxId: Long, innerDir: String?): MutableList<LsItem> {
        return mutableListOf<LsItem>() //TO BE REMOVED
    }

    override fun move(srcBoxId: Long, srcInnerPath: String?, dstBoxId: Long, dstInnerPath: String?) {

    }

    override fun copy(srcBoxId: Long, srcInnerPath: String?, dstBoxId: Long, dstInnerPath: String?) {

    }

    override fun innerMove(boxId: Long, srcInnerPath: String?, dstInnerPath: String?) {

    }
}