package adapters.grpc.dao

class ResponseMessage(
    val positions: Map<String,Position>
){
    lateinit var token: String
}

class Position(
    val position_x: Float,
    val position_y: Float
)
