package warped.realms.dao.entitydao

import adapters.grpc.dao.Position
import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import dao.EntityDao

class EntityDaoMapper {

    val byteArray0 = arrayOf((0).toByte()).toByteArray()
    val byteArray1 = arrayOf((1).toByte()).toByteArray()
    val byteArray2 = arrayOf((2).toByte()).toByteArray()

    fun ByteArray.toFloat(): Float = if (this.contentEquals(byteArray0)) -1f else if(this.contentEquals(byteArray1)) 0f else 1f


    fun MapEntity(request: RequestMessage, indPlayer: Int): EntityDao {
        val input_x = request.input_x.toFloat()
        val input_y = request.input_y.toFloat()
        val input_z = request.input_z.toFloat()
        val mouse_x = request.mouse_x.toFloat()
        val mouse_y = request.mouse_y.toFloat()
        return EntityDao().also {
            it.id = indPlayer
            it.input_x = input_x
            it.input_y = input_y
            it.input_z = input_z
            it.mouse_x = mouse_x
            it.mouse_y = mouse_y
        }
    }
    fun UnmapEntity(entity: EntityDao): ResponseMessage {
        return ResponseMessage(
            entity.positions.map { position -> (position.name to Position(position.pos_x, position.pos_y)) }.toMap()
        ).also { it.token = "" }
    }
}
