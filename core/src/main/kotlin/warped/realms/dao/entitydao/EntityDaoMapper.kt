package warped.realms.dao.entitydao

import adapters.grpc.dao.Position
import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import dao.EntityDao

class EntityDaoMapper {
    fun MapEntity(request: RequestMessage, indPlayer: Int): EntityDao {
        val input_x = request.input_x.toString().toFloat()-1f
        val input_y = request.input_y.toString().toFloat()-1f
        val input_z = request.input_z.toString().toFloat()
        val mouse_x = request.mouse_x.toString().toFloat()
        val mouse_y = request.mouse_y.toString().toFloat()
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
