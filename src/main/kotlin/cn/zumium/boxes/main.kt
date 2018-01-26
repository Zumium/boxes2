package cn.zumium.boxes

import cn.zumium.boxes.config.ConfigManager
import cn.zumium.boxes.db.DbManager
import cn.zumium.boxes.di.DiCenter
import cn.zumium.boxes.rpc.RpcManager
import com.github.salomonbrys.kodein.instance

fun main(args: Array<String>) {
    val configManager = DiCenter.instance<ConfigManager>()
    val dbManager = DiCenter.instance<DbManager>()
    val rpcManager = DiCenter.instance<RpcManager>()

    try {
        configManager.init()
        dbManager.init()
        rpcManager.serve()
    }
    catch (e: Exception) {
        System.err.println(e)
    }
}
