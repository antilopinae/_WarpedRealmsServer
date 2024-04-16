package warped.realms.entity.mapper

import warped.realms.entity.EntityDismapper
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

    fun MapEntity(): EntityDismapper {
        TODO()
    }

    fun DismapEntity(p: EntityDismapper) {
        TODO()
    }
}
