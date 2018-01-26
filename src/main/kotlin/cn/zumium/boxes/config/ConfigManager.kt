package cn.zumium.boxes.config

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware

class ConfigManager(override val kodein: Kodein) : KodeinAware{
    private val kHomeEnvVariableKey = "BOXES_HOME"
    private val kDbFile = "main.db"
    private val kBoxBaseDirName = "boxBase"
    private val kArchiveBaseDirName = "archiveBase"

    private var home = ""

    fun init() {
        home = System.getenv(kHomeEnvVariableKey) ?: throw ConfigException("env variable BOXES_HOME not found")
    }

    fun dbPath() = home + kDbFile
    fun boxBase() = home + kBoxBaseDirName
    fun archiveBase() = home + kArchiveBaseDirName
}