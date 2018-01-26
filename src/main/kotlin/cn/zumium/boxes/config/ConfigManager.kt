package cn.zumium.boxes.config

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware

class ConfigManager(override val kodein: Kodein) : KodeinAware{
    private val kHomeEnvVariableKey = "BOXES_HOME"
    private val kPortEnvVariableKey = "BOXES_PORT"
    private val kDbFile = "main.db"
    private val kBoxBaseDirName = "boxBase"
    private val kArchiveBaseDirName = "archiveBase"

    private var home = ""
    private var port = 6077

    fun init() {
        home = System.getenv(kHomeEnvVariableKey) ?: throw ConfigException("env variable BOXES_HOME not found")
        val p = System.getenv(kPortEnvVariableKey)
        if (p != null) {
            port = try { p.toInt() } catch (e: Exception) { throw ConfigException("invalid BOXES_PORT setting: ${e.message}") }
        }
    }

    fun dbPath() = home + kDbFile
    fun boxBase() = home + kBoxBaseDirName
    fun archiveBase() = home + kArchiveBaseDirName
    fun port() = port
}