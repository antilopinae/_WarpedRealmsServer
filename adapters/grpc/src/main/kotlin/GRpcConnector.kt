package adapters.grpc.server

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import com.grpc.HelloResponse
import com.grpc.PlayerPosition
import io.grpc.stub.StreamObserver
import io.netty.util.internal.ConcurrentSet
import java.util.LinkedList
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.ConcurrentSkipListSet

class GRpcConnector(
    val queue_response: ConcurrentHashMap<Observer, ConcurrentLinkedQueue<ResponseMessage>>,
    val queue_request: ConcurrentHashMap<Observer, ConcurrentLinkedQueue<RequestMessage>>
)
{
    val observers: LinkedHashSet<StreamObserver<HelloResponse>> = linkedSetOf()

    private val request_queues: LinkedList<ConcurrentLinkedQueue<RequestMessage>> = LinkedList()
    private val response_queues: LinkedList<ConcurrentLinkedQueue<ResponseMessage>> = LinkedList()

    private val cachedObservers: LinkedHashMap<StreamObserver<HelloResponse>, Pair<Observer, Int>> = LinkedHashMap()

    fun getMessage(observer: StreamObserver<HelloResponse>, request: RequestMessage){
        val (_observer, _index) = cachedObservers[observer]!!
        queue_request[_observer]!!.add(request)

//        queue_request.add(mapObserver(observer) to request)
        println("[[GRPC Connector]] Get Message input_x: ${request.input_x}")
        //client sent some message lets add it to queue
        //some actions here
//        println("${request.input_x} + ${request.input_y}")
        sendResponses(_observer)
        clearUnusefulQueue()
    }
    fun sendResponses(observer: Observer){
        if(queue_response.isNotEmpty()){
            val linkedQueue = queue_response[observer]!!
            if(linkedQueue.size > 0)
            {
                val response = linkedQueue.poll()
                observer.observer.onNext(mapResponse(response))
                linkedQueue.clear()

                println("[GRpc Connector] SEND RESPONSES with positions: ${response.positions.size}")
            }
            else
            {
                println("[GRpc Connector] NO MESSAGES")
            }
        }
    }
    fun clearUnusefulQueue()
    {
        queue_response.forEach { _, queue ->
            if(queue.size > 50)
            {
                val el = queue.poll()
                queue.clear()
                queue.add(el)
            }
        }
    }
    fun removeObserver(observer: StreamObserver<HelloResponse>){
        observers.remove(observer)
        println("REMOVE OBSERVER")
    }
    fun addObserver(observer: StreamObserver<HelloResponse>){
        observers.add(observer)
        if(cachedObservers[observer] == null)
        {
            val index = response_queues.size

            cachedObservers[observer] = mapObserver(observer) to index

            response_queues.add(ConcurrentLinkedQueue())
            request_queues.add(ConcurrentLinkedQueue())

            val obs = cachedObservers[observer]!!.first
            queue_request[obs] = request_queues[index]
            queue_response[obs] = response_queues[index]
            println("ADD OBSERVER TO CACHED OBSERVERS")
        }
        else
        {
            throw Exception("GRPC COnnector: addObserver")
        }
        println("CONNECTED OBSERVER")
    }
    private fun mapObserver(observer: StreamObserver<HelloResponse>): Observer {
        return Observer(observer)
    }
    fun mapResponse(response: ResponseMessage): HelloResponse {
        val hello_response = HelloResponse.newBuilder()
            .setToken(response.token)
            .also {
                for(i in 0..response.positions.size-1){
                    it.addPositions(
                        PlayerPosition.newBuilder()
                            .setPlayer("You${i}")
                            .setPositionX(response.positions.toList()[i].second.position_x)
                            .setPositionY(response.positions.toList()[i].second.position_y)
                            .build()
                    )
                }
            }
            .build()
        return hello_response
    }
}
