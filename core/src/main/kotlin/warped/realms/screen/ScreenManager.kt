package warped.realms.screen

import WarpedRealms
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.IntMap
import ktx.app.KtxScreen

class ScreenManager private constructor() : Disposable {
    private lateinit var game: WarpedRealms
    private val screens = IntMap<KtxScreen>()

    fun init(game: WarpedRealms) {
        this.game = game
    }
    fun show(screen: ScreenEnum) {
        if (!screens.containsKey(screen.ordinal)) {
            screens.put(screen.ordinal, screen.screenInstance)
        }
        game.addScreen(screens[screen.ordinal])
        game.setScreen<KtxScreen>()
    }
    fun hide(screen: ScreenEnum){
        game.removeScreen<KtxScreen>()
    }
    fun dispose(screen: ScreenEnum) {
        if (!screens.containsKey(screen.ordinal)) return
        screens.remove(screen.ordinal).dispose()
    }
    override fun dispose() {
        for (screen in screens.values()) {
            screen.dispose()
        }
        screens.clear()
        instance = null
    }
    companion object {
        private var instance: ScreenManager? = null
        fun getInstance(): ScreenManager {
            if (instance == null) {
                instance = ScreenManager()
            }
            return instance!!
        }
    }
}
