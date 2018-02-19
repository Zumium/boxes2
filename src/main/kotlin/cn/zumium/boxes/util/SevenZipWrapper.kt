package cn.zumium.boxes.util

import cn.zumium.boxes.config.ConfigManager
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import org.apache.commons.io.FileUtils.forceMkdir
import java.nio.file.Path

class SevenZipWrapper(override val kodein: Kodein) : KodeinAware {
    private val configManager = instance<ConfigManager>()

    fun compressDirectory(dir: Path, compressAs: Path) {
        val pb = ProcessBuilder(configManager.sevenZip(), "a", "-r", compressAs.toString(), ".")
        pb.directory(dir.toFile())
        pb.start().waitFor()
    }

    fun decompressDirectory(archive: Path, decompressAt: Path) {
        forceMkdir(decompressAt.toFile())
        val pb = ProcessBuilder(configManager.sevenZip(), "x", archive.toString())
        pb.directory(decompressAt.toFile())
        pb.start().waitFor()
    }
}