package cn.zumium.boxes.rpc

import cn.zumium.boxes.config.ConfigManager
import cn.zumium.boxes.controller.BoxController
import cn.zumium.boxes.controller.FileController
import cn.zumium.boxes.controller.LinkController
import cn.zumium.boxes.thrift.BoxService
import cn.zumium.boxes.thrift.FileService
import cn.zumium.boxes.thrift.LinkService
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import org.apache.thrift.TMultiplexedProcessor
import org.apache.thrift.server.TServer
import org.apache.thrift.server.TThreadPoolServer
import org.apache.thrift.transport.TServerSocket
import org.apache.thrift.transport.TServerTransport
import java.net.InetSocketAddress

class RpcManager(override val kodein: Kodein) : KodeinAware {
    private val configManager = instance<ConfigManager>()

    fun serve() {
        val processors = TMultiplexedProcessor()
        processors.registerProcessor("BoxService", BoxService.Processor(instance<BoxController>()))
        processors.registerProcessor("FileService", FileService.Processor(instance<FileController>()))
        processors.registerProcessor("LinkService", LinkService.Processor(instance<LinkController>()))

        val transport: TServerTransport = TServerSocket(InetSocketAddress("localhost", configManager.port()))
        val server: TServer = TThreadPoolServer(TThreadPoolServer.Args(transport).processor(processors))

        server.serve()
    }
}