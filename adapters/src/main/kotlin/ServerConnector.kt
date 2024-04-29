import grpc.GRpcLayer
import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import rest_api.KtorLayer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentMap


//Подключение к серверу, передача событий.
class ServerConnector(
    private val queue_response: ConcurrentLinkedQueue<ConcurrentHashMap<Observer, ConcurrentLinkedQueue<ResponseMessage>>>,
    private val queue_request: ConcurrentLinkedQueue<ConcurrentHashMap<Observer, ConcurrentLinkedQueue<RequestMessage>>>
) {
    val ktorLayer = KtorLayer() //its first because in grpc there is while(true) process
    val grpcLayer = GRpcLayer(queue_response, queue_request)
    init {
        println("==========Server To Start==========")
    }
    fun dispose() {
        grpcLayer.stopConnection()
    }
}
