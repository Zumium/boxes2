package cn.zumium.boxes.util

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

val UtilModule = Kodein.Module {
    bind<SevenZipUtil>() with singleton { SevenZipUtil(kodein) }
}