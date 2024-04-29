package warped.realms.server

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import server_logic.server.ServerGameLogicController
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentMap

class ServerGameLogicBuilder(
    private val queue_response: ConcurrentLinkedQueue<ConcurrentHashMap<Observer, ConcurrentLinkedQueue<ResponseMessage>>>,
    private val queue_request: ConcurrentLinkedQueue<ConcurrentHashMap<Observer, ConcurrentLinkedQueue<RequestMessage>>>
)
{
    private val game_logic = ServerGameLogicController(queue_response, queue_request)
    init {
        println("Game logic started")
    }
    fun Start(){
        game_logic.CreateGameLogicRoom(queue_response.peek(), queue_request.peek())
    }
    fun Stop() {
        TODO()
    }
}
