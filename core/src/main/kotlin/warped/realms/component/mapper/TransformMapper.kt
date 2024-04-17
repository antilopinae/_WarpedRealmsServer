package warped.realms.component.mapper

import dao.EntityDao
import ktx.math.vec2
import warped.realms.component.TransformComponent

class TransformMapper(
    var trackerCmp: TransformComponent
) {
    val mapperCmp = TransformComponent(vec2(trackerCmp.location.x, trackerCmp.location.y))
    fun Update() {
        mapperCmp.location = vec2(trackerCmp.location.x, trackerCmp.location.y)
    }
    fun MapTransformComponent(entity: EntityDao){
        //nothing to do
    }
    fun DismapTransformComponent(entity: EntityDao){
        //nothing to do
    }
    companion object {
        const val SIZE_ARRAY = 2
    }
}
