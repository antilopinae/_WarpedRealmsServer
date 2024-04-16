package warped.realms.gdx

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import ktx.assets.toInternalFile
import warped.realms.resources.ResourceController
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.ReentrantLock

class GdxWindowController {
    private val lock: ReentrantLock = ReentrantLock()

    init {

    }

    companion object{
        private val instance: AtomicBoolean = AtomicBoolean(false)
        private lateinit var resourceController: GdxWindowController
        private val lock = ReentrantLock()

        fun getInstance(): GdxWindowController {
            lock.lock()
            try{
                if(!instance.get()){
                    instance.set(true)
                    resourceController = GdxWindowController()
                }
                return resourceController
            } finally{
                lock.unlock()
            }
        }
    }
}
