package cn.zumium.boxes.util

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import org.apache.commons.io.FileUtils.forceMkdir
import java.nio.file.Path

class SevenZipWrapper(override val kodein: Kodein) : KodeinAware {

    fun compressDirectory(dir: Path, compressAs: Path) {
        val pb = ProcessBuilder("7z", "a", "-r", compressAs.toString(), ".")
        pb.directory(dir.toFile())
        pb.start().waitFor()
    }

    fun decompressDirectory(archive: Path, decompressAt: Path) {
        forceMkdir(decompressAt.toFile())
        val pb = ProcessBuilder("7z", "x", archive.toString())
        pb.directory(decompressAt.toFile())
        pb.start().waitFor()
    }
}