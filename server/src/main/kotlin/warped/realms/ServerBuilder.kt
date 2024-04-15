package warped.realms

import GRpcBuilder
import KtorBuilder
import ServerConnector
import ServerGameLogicBuilder
import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import java.util.concurrent.ConcurrentLinkedQueue

class ServerBuilder {

    fun build(){
        System.setProperty("io.ktor.development", "true")
        val queue_response: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>> = ConcurrentLinkedQueue()
        val queue_request: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>> = ConcurrentLinkedQueue()

        val grpc = Thread{
            val serverConnector = ServerConnector(queue_response, queue_request)
            val grpc_builder = GRpcBuilder(serverConnector)
        }
        grpc.start()

        val ktor = Thread{
            val ktor_builder = KtorBuilder()
            ktor_builder.start()
        }
        ktor.start()

        val game_logic = Thread{
            val game_logic_builder = ServerGameLogicBuilder(queue_response, queue_request)
            game_logic_builder.Start()
        }
        game_logic.start()
    }
}
