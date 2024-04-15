package server_logic.server

import ServerConnector
import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import java.util.concurrent.ConcurrentLinkedQueue

class ServerGameLogicController(
    val queue_response: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>>,
    val queue_request: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>>
) {
    private val gameLogicRooms: MutableList<ServerGameLogicRoom> = mutableListOf()

    fun CreateGameLogicRoom(
        queue_response: ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>,
        queue_request: ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>
    ){
        val room = createGameLogicRoom(queue_response, queue_request)
        gameLogicRooms.add(room)
        startGameLogicRoom(room)
    }
    private fun createGameLogicRoom(
        queue_response: ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>,
        queue_request: ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>
    ): ServerGameLogicRoom {
        return ServerGameLogicRoom(queue_response, queue_request)
    }
    private fun startGameLogicRoom(room: ServerGameLogicRoom){
        room.StartRoom()
    }
    private fun stopGameLogicRoom(id: Int){
        gameLogicRooms[id].StopRoom()
    }
    private fun stopGameLogicRoom(room: ServerGameLogicRoom){
        gameLogicRooms[gameLogicRooms.indexOf(room)].StopRoom()
    }
    private fun stopAllGameLogicRooms(){
        gameLogicRooms.forEach { it.StopRoom() }
    }
}
