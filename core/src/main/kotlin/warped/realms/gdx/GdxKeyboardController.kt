package warped.realms.gdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import ktx.app.KtxInputAdapter
import warped.realms.component.MoveComponent
import warped.realms.input.PlayerKeyboardInputProcessor
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.ReentrantLock

class GdxKeyboardController: KtxInputAdapter {
    private val lock: ReentrantLock = ReentrantLock()
    //some logic
    private val moveCmps: MutableList<MoveComponent> = mutableListOf()

    private var playerCos: Float = 0f
    private var playerSin: Float = 0f
    fun addMoveCmp(moveComponent: MoveComponent) {
        moveCmps.add(moveComponent)
    }
    fun InitialKeyboard() {
        Gdx.input.inputProcessor = this
    }
    fun Dispose() {

    }
    override fun keyDown(keycode: Int): Boolean {
        if (keycode.isMovementKey()) {
            when (keycode) {
                Input.Keys.UP -> playerSin = 1f
                Input.Keys.DOWN -> playerSin = -1f
                Input.Keys.LEFT -> playerCos = -1f
                Input.Keys.RIGHT -> playerCos = 1f
            }
            updatePlayerMovement()
            return true
        }
        return false
    }
    override fun keyUp(keycode: Int): Boolean {
        if (keycode.isMovementKey()) {
            when (keycode) {
                Input.Keys.UP -> playerSin = if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) -1f else 0f
                Input.Keys.DOWN -> playerSin = if (Gdx.input.isKeyPressed(Input.Keys.UP)) 1f else 0f
                Input.Keys.LEFT -> playerCos = if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) 1f else 0f
                Input.Keys.RIGHT -> playerCos = if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) -1f else 0f
            }
            updatePlayerMovement()
            return true
        }
        return false
    }
    private fun updatePlayerMovement() {
        moveCmps.forEach {
            it.apply {
                cos = playerCos
                sin = playerSin
            }
        }
    }
    private fun Int.isMovementKey(): Boolean {
        return this == Input.Keys.UP || this == Input.Keys.DOWN || this == Input.Keys.RIGHT || this == Input.Keys.LEFT
    }
    //some logic
    companion object{
        val logger = ktx.log.logger<PlayerKeyboardInputProcessor>()

        private val instance: AtomicBoolean = AtomicBoolean(false)
        private lateinit var gdxResourcesController: GdxKeyboardController
        private val lock = ReentrantLock()

        fun getInstance(): GdxKeyboardController {
            lock.lock()
            try{
                if(!instance.get()){
                    instance.set(true)
                    gdxResourcesController = GdxKeyboardController()
                }
                return gdxResourcesController
            } finally{
                lock.unlock()
            }
        }
    }
}
