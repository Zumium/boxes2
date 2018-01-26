package cn.zumium.boxes.fs

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

val FsModule = Kodein.Module {
    bind<FsManager>() with singleton { FsManager(kodein) }
}