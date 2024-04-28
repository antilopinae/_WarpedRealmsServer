package adapters.grpc.server

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import com.grpc.HelloResponse
import com.grpc.PlayerPosition
import io.grpc.stub.StreamObserver
import java.util.concurrent.ConcurrentLinkedQueue

class GRpcConnector(
    val queue_response: ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>,
    val queue_request: ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>
) {
    val observers: LinkedHashSet<StreamObserver<HelloResponse>> = linkedSetOf()

    fun getMessage(observer: StreamObserver<HelloResponse>, request: RequestMessage){
        queue_request.add(mapObserver(observer) to request)
        println("[[GRPC Connector]] Get Message input_x: ${request.input_x}")
        //client sent some message lets add it to queue
        //some actions here
//        println("${request.input_x} + ${request.input_y}")
        sendResponses()
    }
    fun sendResponses(){
//        while(queue_response.isNotEmpty()){
//            val (observer, response) = queue_response.poll()
//            observer.observer.onNext(mapResponse(response))
//        }
        if(queue_response.isNotEmpty()){
            while(queue_response.size != 1)
            {
                queue_response.poll()
            }
            val (observer, response) = queue_response.poll()
            observer.observer.onNext(mapResponse(response))
            println(response.positions.toList()[0].second.position_x)
        }
    }
    fun removeObserver(observer: StreamObserver<HelloResponse>){
        observers.remove(observer)
    }
    fun addObserver(observer: StreamObserver<HelloResponse>){
        observers.add(observer)
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
