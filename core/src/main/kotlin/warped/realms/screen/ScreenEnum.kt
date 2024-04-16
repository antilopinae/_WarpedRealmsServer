package warped.realms.screen

import WarpedRealms
import generated.systems.Factories
import generated.systems.Systems
import ktx.app.KtxScreen

enum class ScreenEnum
{
    LOAD_SCREEN{
        override fun ScreenInstance(): KtxScreen {
            return LoaderScreen()
        }
    },
    SERVER_SCREEN{
        override fun ScreenInstance(): KtxScreen {
            return ServerScreen()
        }
    },
    GAME {
        override fun ScreenInstance(): AScreen {
            val factories = Factories()
            val systems = Systems(factories)
            return GameScreen(KeeperGame.game, systems)
        }
    };
    protected abstract fun ScreenInstance(): KtxScreen
    val screenInstance: KtxScreen
        get() = this.ScreenInstance()
}
class KeeperGame{
    companion object{
        lateinit var game: WarpedRealms
    }
}
