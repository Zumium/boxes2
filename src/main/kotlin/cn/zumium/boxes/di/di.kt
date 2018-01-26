package cn.zumium.boxes.di

import cn.zumium.boxes.config.ConfigModule
import cn.zumium.boxes.db.DbModule
import cn.zumium.boxes.rpc.RpcModule
import com.github.salomonbrys.kodein.Kodein

val DiCenter = Kodein {
    import(ConfigModule)
    import(DbModule)
    import(RpcModule)
}