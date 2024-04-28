package adapters.grpc.server

import adapters.grpc.dao.RequestMessage
import com.grpc.*
import io.grpc.stub.StreamObserver

class GRpcService(
    val responseObserver: StreamObserver<HelloResponse>,
    val connector: GRpcConnector
): StreamObserver<HelloRequest> {
    override fun onNext(p0: HelloRequest) {
        // receiving the data from client
//        println("${p0!!}")
        connector.getMessage(responseObserver, unmapRequest(p0))
    }
    override fun onCompleted() {
        connector.removeObserver(responseObserver)
    }
    override fun onError(p0: Throwable?) {
        connector.removeObserver(responseObserver)
    }
    fun unmapRequest(packClient: HelloRequest): RequestMessage{
        return RequestMessage(
            packClient.token,
            packClient.message.input.inputX,
            packClient.message.input.inputY,
            packClient.message.input.inputZ,
            packClient.message.input.mouseX,
            packClient.message.input.mouseY
        )
    }
}
