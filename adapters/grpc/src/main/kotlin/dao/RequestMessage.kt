package adapters.grpc.dao

import com.google.protobuf.ByteString

class RequestMessage (
    val token: String,
    val input_x: ByteArray,
    val input_y: ByteArray,
    val input_z: ByteArray,
    val mouse_x: ByteArray,
    val mouse_y: ByteArray
)

