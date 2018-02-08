package cn.zumium.boxes.di

import cn.zumium.boxes.config.ConfigModule
import cn.zumium.boxes.controller.ControllerModule
import cn.zumium.boxes.db.DbModule
import cn.zumium.boxes.fs.FsModule
import cn.zumium.boxes.rpc.RpcModule
import cn.zumium.boxes.util.UtilModule
import com.github.salomonbrys.kodein.Kodein

val DiCenter = Kodein {
    import(ConfigModule)
    import(ControllerModule)
    import(DbModule)
    import(FsModule)
    import(UtilModule)
    import(RpcModule)
}