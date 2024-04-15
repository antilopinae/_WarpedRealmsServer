package warped.realms.component.mapper

import ktx.math.vec2
import warped.realms.component.TransformComponent

class TransformMapper(
    var trackerCmp: TransformComponent
) {
    val mapperCmp = TransformComponent(vec2(trackerCmp.location.x, trackerCmp.location.y))
    fun Update() {
        mapperCmp.location = vec2(trackerCmp.location.x, trackerCmp.location.y)
    }

    companion object {
        const val SIZE_ARRAY = 2
//        fun TransformComponent.map(): ByteArray {
//            val p = ByteArray(2)
//            p[0] = this.location.x.toInt().toByte()
//            p[1] = this.location.y.toInt().toByte()
//            return p
//        }

        fun TransformComponent.dismap() {
//            this.location = vec2(p.position_x.toFloat(), p.position_y.toFloat())
//            this.location = vec2(500f, 200f)
        }
    }
}
