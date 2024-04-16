package grpc

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.GRpcServer
import adapters.grpc.server.dao.Observer
import java.util.concurrent.ConcurrentLinkedQueue

class GRpcLayer(
    val queue_response: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>>,
    val queue_request: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>>
) {
    val server = GRpcServer(queue_response.peek(), queue_request.peek()).also { it.start() }
    fun stopConnection(){
        server.stopConnection()
    }
}
