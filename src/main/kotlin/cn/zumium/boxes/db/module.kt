package cn.zumium.boxes.db

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

val DbModule = Kodein.Module {
    bind<DbManager>() with singleton { DbManager(kodein) }
}