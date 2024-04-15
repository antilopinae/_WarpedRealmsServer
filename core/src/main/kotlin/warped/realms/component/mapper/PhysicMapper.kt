package warped.realms.component.mapper

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
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

    companion object {
        const val SIZE_ARRAY = 2
       fun PhysicComponent.dismap() {
//           this.body!!.setTransform(p.position_x.toFloat(), p.position_y.toFloat(), this.body!!.angle)
       }
    }
}
