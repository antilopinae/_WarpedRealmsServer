package warped.realms.resources

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import ktx.assets.toInternalFile
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.ReentrantLock

class ResourceController {
    private val lock: ReentrantLock = ReentrantLock()
    private val textureAtlas: TextureAtlas by lazy{ TextureAtlas("graphics/gameObject.atlas".toInternalFile()) }

    fun GetMap(){

    }
    fun GetAtlas(): TextureAtlas{
        lock.lock()
        try {
            return textureAtlas
        } catch( e: Exception){
            println(e.stackTraceToString())
            return textureAtlas
        }
        finally {
            lock.unlock()
        }
    }
    companion object{
        private val instance: AtomicBoolean = AtomicBoolean(false)
        private lateinit var resourceController: ResourceController
        private val lock = ReentrantLock()

        fun getInstance(): ResourceController {
            lock.lock()
            try {
                if (!instance.get()) {
                    instance.set(true)
                    resourceController = ResourceController()
                }
                return resourceController
            } finally {
                lock.unlock()
            }
        }
    }
}
