package dao

import kotlin.properties.Delegates

//class with minimum memory to save some states
class EntityDao{
    var id by Delegates.notNull<Int>()
    var input_x by Delegates.notNull<Float>()
    var input_y by Delegates.notNull<Float>()
    var input_z by Delegates.notNull<Float>()
    var mouse_x by Delegates.notNull<Float>()
    var mouse_y by Delegates.notNull<Float>()

    lateinit var positions: MutableList<PlayerPosition>
}
class PlayerPosition(
    var name: String,
    val pos_x: Float,
    val pos_y: Float
)
