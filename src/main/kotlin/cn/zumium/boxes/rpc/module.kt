package cn.zumium.boxes.rpc

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

val RpcModule = Kodein.Module {
    bind<RpcManager>() with singleton { RpcManager(kodein) }
}