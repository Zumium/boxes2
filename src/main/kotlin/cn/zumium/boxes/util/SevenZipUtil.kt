package cn.zumium.boxes.util

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import java.nio.file.Path

class SevenZipUtil(override val kodein: Kodein) : KodeinAware {

    fun compressDirectory(dir: Path, compressAs: Path) {
        val pb = ProcessBuilder("7z", "a", "-r", compressAs.toString(), dir.toString())
        pb.start().waitFor()
    }

    fun decompressDirectory(archive: Path, decompressAt: Path) {
        val pb = ProcessBuilder("7z", "x", archive.toString())
        pb.directory(decompressAt.toFile())
        pb.start().waitFor()
    }
}