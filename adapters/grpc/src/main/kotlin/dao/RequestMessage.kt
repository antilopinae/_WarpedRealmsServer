package adapters.grpc.dao

import com.google.protobuf.ByteString

class RequestMessage (
    val token: String,
    val input_x: Int,
    val input_y: Int,
    val input_z: Int,
    val input_mouse_x: Int,
    val input_mouse_y: Int,
)

