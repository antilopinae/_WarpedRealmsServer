package grpc

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.GRpcServer
import adapters.grpc.server.dao.Observer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentMap

class GRpcLayer(
    private val queue_response: ConcurrentLinkedQueue<ConcurrentHashMap<Observer, ConcurrentLinkedQueue<ResponseMessage>>>,
    private val queue_request: ConcurrentLinkedQueue<ConcurrentHashMap<Observer, ConcurrentLinkedQueue<RequestMessage>>>
) {
    val server = GRpcServer(queue_response.peek(), queue_request.peek()).also { it.start() }
    fun stopConnection(){
        server.stopConnection()
    }
}
