package adapters.grpc.server.dao

import com.grpc.HelloResponse
import io.grpc.stub.StreamObserver

class Observer (
    val observer: StreamObserver<HelloResponse>
)
