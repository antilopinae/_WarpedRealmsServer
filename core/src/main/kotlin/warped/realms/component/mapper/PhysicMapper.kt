package warped.realms.component.mapper

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import dao.EntityDao
import dao.PlayerPosition
import ktx.box2d.body
import ktx.box2d.box
import ktx.math.vec2
import warped.realms.component.PhysicComponent
import warped.realms.component.TransformComponent
import warped.realms.system.update.PhysicSystem

class PhysicMapper (
    var trackerCmp: PhysicComponent
) {
    val mapperCmp: TransformComponent = TransformComponent(
        vec2(trackerCmp.body!!.position.x, trackerCmp.body!!.position.y)
    )
    fun Update() {
        mapperCmp.location = vec2(trackerCmp.body!!.position.x, trackerCmp.body!!.position.y)
    }
    fun MapPhysicComponent(entity: EntityDao){
        entity.apply {
            this.positions = mutableListOf<PlayerPosition>().also { it.clear() }
            this.positions.add(PlayerPosition("You", mapperCmp.location.x, mapperCmp.location.y))
        }
    }
    fun DismapPhysicComponent(entity: EntityDao){
        trackerCmp.apply {
           this.body!!.setTransform(entity.positions[0].pos_x.toFloat(), entity.positions[0].pos_y.toFloat(), this.body!!.angle)
        }
    }
    companion object {
        const val SIZE_ARRAY = 2
    }
}
