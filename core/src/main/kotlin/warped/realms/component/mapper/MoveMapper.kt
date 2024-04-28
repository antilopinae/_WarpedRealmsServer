package warped.realms.component.mapper

import dao.EntityDao
import warped.realms.component.MoveComponent

class MoveMapper(
    var trackerCmp: MoveComponent
) {
    val mapperCmp = MoveComponent(
        trackerCmp.speed,
        trackerCmp.cos,
        trackerCmp.sin
    )
    fun Update() {
        mapperCmp.speed = trackerCmp.speed
        mapperCmp.cos = trackerCmp.cos
        mapperCmp.sin = trackerCmp.sin
    }
    fun MapMoveComponent(entity: EntityDao){
        entity.apply {
            this.input_x = mapperCmp.cos
            this.input_y = mapperCmp.sin
        }
    }
    fun DismapMoveComponent(entity: EntityDao){
        trackerCmp.apply {
            this.speed = 8f
            this.sin = entity.input_y
            this.cos = entity.input_x
        }
    }
}
