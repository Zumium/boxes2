package cn.zumium.boxes.config

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import java.nio.file.Paths.get

class ConfigManager(override val kodein: Kodein) : KodeinAware{
    private val kHomeEnvVariableKey = "BOXES_HOME"
    private val kPortEnvVariableKey = "BOXES_PORT"
    private val kSevenZipExecEnvVariableKey = "BOXES_SEVENZ_EXEC"

    private val kDbFile = "main.db"
    private val kBoxBaseDirName = "boxBase"
    private val kArchiveBaseDirName = "archiveBase"
    private val kArchiveExtension = "box"

    private var home = ""
    private var port = 6077
    private var sevenZipPath = "/usr/bin/7z"

    fun init() {
        home = System.getenv(kHomeEnvVariableKey) ?: throw ConfigException("env variable BOXES_HOME not found")

        val p = System.getenv(kPortEnvVariableKey)
        if (p != null)
            port = try { p.toInt() } catch (e: Exception) { throw ConfigException("invalid BOXES_PORT setting: ${e.message}") }

        val sevenz = System.getenv(kSevenZipExecEnvVariableKey)
        if (sevenz != null)
            sevenZipPath = sevenz
    }

    fun dbPath() = get(home, kDbFile)
    fun boxBase() = get(home, kBoxBaseDirName)
    fun archiveBase() = get(home, kArchiveBaseDirName)
    fun port() = port
    fun archiveExtension() = kArchiveExtension
    fun sevenZip() = sevenZipPath
}