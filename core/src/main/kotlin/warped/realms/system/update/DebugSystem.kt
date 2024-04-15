package warped.realms.system.update

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.assets.disposeSafely
import System
import Update
import generated.systems.Factories
import generated.systems.Systems

@System
@Update(1)
class DebugSystem(
    val systems: Systems,
    val factories: Factories
) {
    private val phWorld: World = PhysicSystem.phWorld
    private val stage: Stage by lazy{ RenderSystem.stage }

    private val physicRenderer: Box2DDebugRenderer by lazy { Box2DDebugRenderer() }

    fun Update(deltaTime: Float) {
        physicRenderer.render(phWorld, stage.camera.combined)
    }

    fun Dispose() {
        println("[DISPOSE] ${this::class.simpleName}")
        physicRenderer.disposeSafely()
    }
}
