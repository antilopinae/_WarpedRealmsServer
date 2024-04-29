package server_logic.server

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import warped.realms.server.gamelogic.GameLogicThread
import warped.realms.server.gamelogic.ServerGameLogic
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.locks.ReentrantLock

class ServerGameLogicRoom(
    private val queue_response: ConcurrentHashMap<Observer, ConcurrentLinkedQueue<ResponseMessage>>,
    private val queue_request: ConcurrentHashMap<Observer, ConcurrentLinkedQueue<RequestMessage>>
)
{
//    private val roomsPhysicLogic: ConcurrentLinkedQueue<ServerGameLogic> = ConcurrentLinkedQueue()
    private val lockGameLogic = ReentrantLock().also { it.lock() }
    private val serverGameLogic = ServerGameLogic()
    private val threadsGameLogic: MutableList<GameLogicThread> = mutableListOf()
    fun StartRoom(){
        if(!lockGameLogic.isLocked) lockGameLogic.lock()
        createPhysicRoom().start()
    }
    fun StopRoom(){
        threadsGameLogic.stream().forEach {
//            it.StopPhysic()
        }
        //count results
        // lock rooms
        threadsGameLogic.stream().forEach {
//            it.CleanGameLogic()
        }
        //clean all
    }
    private fun createPhysicRoom(): GameLogicThread {
        val gameLogic = GameLogicThread(serverGameLogic, lockGameLogic, queue_response, queue_request)
        threadsGameLogic.add(gameLogic)
        return gameLogic
    }
    private fun stopPhysicRoom(){
        if(lockGameLogic.isLocked) lockGameLogic.unlock()
        threadsGameLogic.forEach {  it.join() }
    }
}
