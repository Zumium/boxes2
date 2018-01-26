package cn.zumium.boxes.config

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

val ConfigModule = Kodein.Module {
    bind<ConfigManager>() with singleton { ConfigManager(kodein) }
}