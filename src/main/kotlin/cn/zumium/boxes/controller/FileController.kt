package cn.zumium.boxes.controller

import cn.zumium.boxes.db.DbManager
import cn.zumium.boxes.fs.FsManager
import cn.zumium.boxes.thrift.AddBy
import cn.zumium.boxes.thrift.FetchBy
import cn.zumium.boxes.thrift.FileService.Iface
import cn.zumium.boxes.thrift.LsItem
import cn.zumium.boxes.util.reportException
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import org.jetbrains.exposed.sql.transactions.transaction

class FileController(override val kodein: Kodein) : Iface, KodeinAware {
    private val dbManager = instance<DbManager>()
    private val fsManager = instance<FsManager>()

    override fun add(boxId: Long, innerPath: String, outerPath: String, addBy: AddBy) {
        reportException("adding file or dir to box") {
            transaction {
                val box = dbManager.box.getBox(boxId)
                fsManager.file.add(box.name, innerPath, outerPath, addBy)
            }
        }
    }

    override fun fetch(boxId: Long, innerPath: String, outerPath: String, fetchBy: FetchBy) {
        reportException("fetching file or dir from box") {
            transaction {
                val box = dbManager.box.getBox(boxId)
                fsManager.file.fetch(box.name, innerPath, outerPath, fetchBy)
            }
        }
    }

    override fun remove(boxId: Long, innerPath: String) {
        reportException("removing file or dir from box") {
            transaction {
                val box = dbManager.box.getBox(boxId)
                fsManager.file.remove(box.name, innerPath)
            }
        }
    }

    override fun ls(boxId: Long, innerDir: String): List<LsItem> = reportException("listing files and dirs") {
        transaction {
            val box = dbManager.box.getBox(boxId)
            fsManager.file.ls(box.name, innerDir)
        }
    }

    override fun move(srcBoxId: Long, srcInnerPath: String, dstBoxId: Long, dstInnerPath: String) {
        reportException("moving file or dir between boxes") {
            transaction {
                val srcBox = dbManager.box.getBox(srcBoxId)
                val dstBox = dbManager.box.getBox(dstBoxId)
                fsManager.file.moveBetweenBox(srcBox.name, srcInnerPath, dstBox.name, dstInnerPath)
            }
        }
    }

    override fun copy(srcBoxId: Long, srcInnerPath: String, dstBoxId: Long, dstInnerPath: String) {
        reportException("copying file or dir between boxes") {
            transaction {
                val srcBox = dbManager.box.getBox(srcBoxId)
                val dstBox = dbManager.box.getBox(dstBoxId)
                fsManager.file.copyBetweenBox(srcBox.name, srcInnerPath, dstBox.name, dstInnerPath)
            }
        }
    }

    override fun innerMove(boxId: Long, srcInnerPath: String, dstInnerPath: String) {
        reportException("moving file or dir inside box") {
            transaction {
                val box = dbManager.box.getBox(boxId)
                fsManager.file.innerMove(box.name, srcInnerPath, dstInnerPath)
            }
        }
    }

    override fun innerCopy(boxId: Long, srcInnerPath: String, dstInnerPath: String) {
        reportException("copying file or dir inside box") {
            transaction {
                val box = dbManager.box.getBox(boxId)
                fsManager.file.innerCopy(box.name, srcInnerPath, dstInnerPath)
            }
        }
    }
}