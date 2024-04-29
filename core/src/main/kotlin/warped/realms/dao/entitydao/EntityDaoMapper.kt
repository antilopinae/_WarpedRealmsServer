package warped.realms.dao.entitydao

import adapters.grpc.dao.Position
import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import dao.EntityDao

class EntityDaoMapper {
    fun MapEntity(request: RequestMessage, indPlayer: Int): EntityDao {
        val input_x = request.input_x.toFloat()
        val input_y = request.input_y.toFloat()
        val input_z = request.input_z.toFloat()
        val mouse_x = request.input_mouse_x.toFloat()
        val mouse_y = request.input_mouse_y.toFloat()
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
    fun UnmapEntity(entity: EntityDao, entities: List<EntityDao>): ResponseMessage {
        entity.positions.addAll(
            entities.filter { _entity -> _entity.id != entity.id }.map {
                it.positions[0]
            }
        )
        return ResponseMessage(
            entity.positions.map { position -> (position.name to Position(position.pos_x, position.pos_y)) }.toMap()
        ).also { it.token = "" }
    }
}
