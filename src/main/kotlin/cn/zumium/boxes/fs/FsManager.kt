package cn.zumium.boxes.fs

import cn.zumium.boxes.config.ConfigManager
import cn.zumium.boxes.thrift.*
import cn.zumium.boxes.util.SevenZipWrapper
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class FsManager(override val kodein: Kodein) : KodeinAware {
    private val configManager = instance<ConfigManager>()
    private val sevenZipWrapper = instance<SevenZipWrapper>()

    inner class BoxManager {
        fun create(name: String) = FileUtils.forceMkdir(boxPath(name).toFile())
        fun remove(name: String) = FileUtils.forceDelete(boxPath(name).toFile())
        fun rename(oldName: String, newName: String) = FileUtils.moveDirectory(boxPath(oldName).toFile(), boxPath(newName).toFile())
    }

    inner class FileManager {
        fun add(boxName: String, innerPath: String, source: String, addBy: AddBy) {
            val srcFile = File(source)
            if (!srcFile.exists())
                throw FsBoxException("source $srcFile not exist")

            when (addBy) {
                AddBy.COPY -> copy(srcFile, boxFullPath(boxName, innerPath).toFile())
                AddBy.MOVE -> move(srcFile, boxFullPath(boxName, innerPath).toFile())
            }
        }

        fun fetch(boxName: String, innerPath: String, outPlace: String, fetchBy: FetchBy) {
            val srcFile = File(boxFullPath(boxName, innerPath).toString())
            if (!srcFile.exists())
                throw FsBoxException("box file $boxName:$innerPath not exist")

            when (fetchBy) {
                FetchBy.COPY -> copy(srcFile, File(outPlace))
                FetchBy.MOVE -> move(srcFile, File(outPlace))
            }
        }

        fun remove(boxName: String, innerPath: String) {
            val removedFile = boxFullPath(boxName, innerPath).toFile()
            if (!removedFile.exists())
                throw FsBoxException("box file $boxName:$innerPath not exist")
            FileUtils.forceDelete(removedFile)
        }

        fun ls(boxName: String, innerDir: String): List<LsItem> {
            val targetDirPath = boxFullPath(boxName, innerDir)
            val targetDir = targetDirPath.toFile()
            if (!targetDir.exists())
                throw FsBoxException("box file $boxName:$innerDir not exist")
            if (!targetDir.isDirectory)
                throw FsBoxException("box file $boxName:$innerDir is not a directory")

            val lsFiles = Files.newDirectoryStream(targetDirPath)
            val lsItems = lsFiles.map { it.toFile() }.map { LsItem(it.name, if (it.isDirectory) LsType.DIR else LsType.FILE) }.toList()
            return lsItems
        }

        fun moveBetweenBox(srcBoxName: String, srcInnerPath: String, dstBoxName: String, dstInnerPath: String) {
            val srcFile = boxFullPath(srcBoxName, srcInnerPath).toFile()
            val dstFile = boxFullPath(dstBoxName, dstInnerPath).toFile()
            if (!srcFile.exists())
                throw FsBoxException("box file $srcBoxName:$srcInnerPath not exist")
            move(srcFile, dstFile)
        }

        fun copyBetweenBox(srcBoxName: String, srcInnerPath: String, dstBoxName: String, dstInnerPath: String) {
            val srcFile = boxFullPath(srcBoxName, srcInnerPath).toFile()
            val dstFile = boxFullPath(dstBoxName, dstInnerPath).toFile()
            if (!srcFile.exists())
                throw FsBoxException("box file $srcBoxName:$srcInnerPath not exist")
            copy(srcFile, dstFile)
        }

        fun innerMove(boxName: String, srcInnerPath: String, dstInnerPath: String) {
            val srcFile = boxFullPath(boxName, srcInnerPath).toFile()
            val dstFile = boxFullPath(boxName, dstInnerPath).toFile()
            if (!srcFile.exists())
                throw FsBoxException("box file $boxName:$srcInnerPath not exist")
            move(srcFile, dstFile)
        }

        fun innerCopy(boxName: String, srcInnerPath: String, dstInnerPath: String) {
            val srcFile = boxFullPath(boxName, srcInnerPath).toFile()
            val dstFile = boxFullPath(boxName, dstInnerPath).toFile()
            if (!srcFile.exists())
                throw FsBoxException("box file $boxName:$srcInnerPath not exist")
            copy(srcFile, dstFile)
        }

        //---------------helper functions----------------

        private fun move(from: File, to: File) {
            if (from.isDirectory) {
                //source is dir
                FileUtils.moveDirectory(from, to)
            } else {
                //source is file
                FileUtils.moveFile(from, to)
            }
        }

        private fun copy(from: File, to: File) {
            if (from.isDirectory) {
                //source is dir
                FileUtils.copyDirectory(from, to)
            } else {
                //source is file
                FileUtils.copyFile(from, to)
            }
        }
    }

    inner class LinkManager {
        fun create(boxName: String, innerPath: String, destination: String, ltype: LinkType) {
            val link = Paths.get(destination)
            val target= boxFullPath(boxName, innerPath)
            when (ltype) {
                LinkType.SOFT -> Files.createSymbolicLink(link, target)
                LinkType.HARD -> Files.createLink(link, target)
            }
        }

        fun remove(linkLocation: String) {
            FileUtils.forceDelete(File(linkLocation))
        }
    }

    inner class ArchiveManager {
        fun archive(boxName: String) = sevenZipWrapper.compressDirectory(boxPath(boxName), archivePath(boxName))

        fun unarchive(boxName: String) = sevenZipWrapper.decompressDirectory(archivePath(boxName), boxPath(boxName))

        fun remove(boxName: String) = FileUtils.forceDelete(archivePath(boxName).toFile())

        fun rename(oldName: String, newName: String) = FileUtils.moveFile(archivePath(oldName).toFile(), archivePath(newName).toFile())
    }

    val box = BoxManager()
    val file = FileManager()
    val link = LinkManager()
    val archive = ArchiveManager()

    //--------------helper functions-------------
//    private fun archivePath(name: String) = get(configManager.archiveBase(), "${name}.${configManager.archiveExtension()}")
    private fun archivePath(name: String) = configManager.archiveBase().resolve("${name}.${configManager.archiveExtension()}").normalize()
//    private fun boxPath(name: String) = get(configManager.boxBase(), name)
    private fun boxPath(name: String) = configManager.boxBase().resolve(name).normalize()
    private fun boxFullPath(name: String, innerPath: String) = boxPath(name).resolve(innerPath).normalize()
}