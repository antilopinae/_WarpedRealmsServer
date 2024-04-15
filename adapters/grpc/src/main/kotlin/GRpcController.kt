package adapters.grpc.server

import com.grpc.GreetingServiceGrpc
import com.grpc.HelloRequest
import com.grpc.HelloResponse
import io.grpc.stub.StreamObserver

class GRpcController(
    val connector: GRpcConnector
): GreetingServiceGrpc.GreetingServiceImplBase() {
    override fun greeting(responseObserver: StreamObserver<HelloResponse>): StreamObserver<HelloRequest> {
        connector.addObserver(responseObserver)
        return GRpcService(responseObserver, connector)
    }
}
