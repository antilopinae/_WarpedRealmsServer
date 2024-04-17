package warped.realms.entity.mapper

import dao.EntityDao
import warped.realms.component.MoveComponent
import warped.realms.component.PhysicComponent
import warped.realms.component.TransformComponent
import warped.realms.entity.GameEntity
import warped.realms.component.mapper.MoveMapper
import warped.realms.component.mapper.PhysicMapper
import warped.realms.component.mapper.TransformMapper

class EntityMapper(
    var entity: GameEntity
) {
    val moveMapper = MoveMapper(entity.getCmp<MoveComponent>())
    val transMapper = TransformMapper(entity.getCmp<TransformComponent>())
    val physMapper = PhysicMapper(entity.getCmp<PhysicComponent>())

    fun Update() {
        moveMapper.Update()
        transMapper.Update()
        physMapper.Update()
    }
    fun MapEntity(): EntityDao {
        val entity = EntityDao()
        moveMapper.MapMoveComponent(entity)
        transMapper.MapTransformComponent(entity)
        physMapper.MapPhysicComponent(entity)
        return entity
    }
    fun DismapEntity(entity: EntityDao) {
        moveMapper.DismapMoveComponent(entity)
        transMapper.DismapTransformComponent(entity)
//        physMapper.DismapPhysicComponent(entity)
    }
}
