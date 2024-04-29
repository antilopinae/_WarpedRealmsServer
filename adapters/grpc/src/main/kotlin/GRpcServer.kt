package adapters.grpc.server

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import io.grpc.Server
import io.grpc.ServerBuilder
import io.ktor.utils.io.errors.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.Executors

class GRpcServer(
    private val queue_response: ConcurrentHashMap<Observer, ConcurrentLinkedQueue<ResponseMessage>>,
    private val queue_request: ConcurrentHashMap<Observer, ConcurrentLinkedQueue<RequestMessage>>
)
{
    val connector = GRpcConnector(queue_response, queue_request)
    val controller = GRpcController(connector)
    val server: Server = ServerBuilder.forPort(8000)
        .executor(Executors.newCachedThreadPool())
        .addService(controller)
        .build()
    fun start(){
        try {
            server.start()
        } catch (e: IOException){
            println("Exception on grpc server started")
        }
        println("GRPC Server started")
        try {
            server.awaitTermination()
        } catch(e: InterruptedException){
            println("Interrupted exception catch")
        }
    }
    fun stopConnection(){
        TODO()
    }
}
