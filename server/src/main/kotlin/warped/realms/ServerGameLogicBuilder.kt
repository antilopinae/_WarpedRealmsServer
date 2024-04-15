import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import server_logic.server.ServerGameLogicController
import java.util.concurrent.ConcurrentLinkedQueue

class ServerGameLogicBuilder(
    val queue_response: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>>,
    val queue_request: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>>
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
