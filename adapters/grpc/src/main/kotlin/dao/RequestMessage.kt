package adapters.grpc.dao

import com.google.protobuf.ByteString

class RequestMessage (
    val token: String,
    val byteArray_x: ByteArray,
    val byteArray_y: ByteArray,
    val byteArray_z: ByteArray,
    val byteArray_mouse_x: ByteArray,
    val byteArray_mouse_y: ByteArray
)

