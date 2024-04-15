package adapters.grpc

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.GRpcServer
import adapters.grpc.server.dao.Observer
import java.util.concurrent.ConcurrentLinkedQueue

class GRpcLayer(
    val queue_response: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>>,
    val queue_request: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>>
) {
    init {
        queue_request.add(ConcurrentLinkedQueue())
        queue_response.add(ConcurrentLinkedQueue())
    }
    val server = GRpcServer(queue_response.peek(), queue_request.peek()).also { it.start() }

    val connector = server.connector
    fun stopConnection(){
        server.stopConnection()
    }
}
