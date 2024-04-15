import adapters.grpc.GRpcLayer
import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import adapters.rest_api.KtorLayer
import java.util.concurrent.ConcurrentLinkedQueue


//Подключение к серверу, передача событий.
class ServerConnector(
    val queue_response: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>>,
    val queue_request: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>>
) {
    val grpcLayer = GRpcLayer(queue_response, queue_request)
    val ktorLayer = KtorLayer()
    init {
        println("==========Server To Start==========")
    }
    fun dispose() {
        grpcLayer.stopConnection()
    }
}
