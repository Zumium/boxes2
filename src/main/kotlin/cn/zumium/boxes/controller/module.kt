package cn.zumium.boxes.controller

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

val ControllerModule = Kodein.Module {
    bind<BoxController>() with singleton { BoxController(kodein) }
    bind<FileController>() with singleton { FileController(kodein) }
    bind<LinkController>() with singleton { LinkController(kodein) }
}