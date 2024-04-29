package server_logic.server

import ServerConnector
import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentMap

class ServerGameLogicController(
    private val queue_response: ConcurrentLinkedQueue<ConcurrentHashMap<Observer, ConcurrentLinkedQueue<ResponseMessage>>>,
    private val queue_request: ConcurrentLinkedQueue<ConcurrentHashMap<Observer, ConcurrentLinkedQueue<RequestMessage>>>
) {
    private val gameLogicRooms: MutableList<ServerGameLogicRoom> = mutableListOf()

    fun CreateGameLogicRoom(
        queue_response: ConcurrentHashMap<Observer, ConcurrentLinkedQueue<ResponseMessage>>,
        queue_request: ConcurrentHashMap<Observer, ConcurrentLinkedQueue<RequestMessage>>
    ){
        val room = createGameLogicRoom(queue_response, queue_request)
        gameLogicRooms.add(room)
        startGameLogicRoom(room)
    }
    private fun createGameLogicRoom(
        queue_response: ConcurrentHashMap<Observer, ConcurrentLinkedQueue<ResponseMessage>>,
        queue_request: ConcurrentHashMap<Observer, ConcurrentLinkedQueue<RequestMessage>>
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
