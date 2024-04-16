import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import ktx.app.KtxGame
import ktx.app.KtxScreen
import warped.realms.screen.CRAssetManager
import warped.realms.screen.KeeperGame
import warped.realms.screen.ScreenEnum
import warped.realms.screen.ScreenManager

//Игровая логика, работа с server_connector
class WarpedRealms : KtxGame<KtxScreen>() {
    //private val camera: OrthographicCamera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    val keeperGame = KeeperGame.apply { this.game = this@WarpedRealms }
    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        ScreenManager.getInstance().init(this)
//        ScreenManager.getInstance().show(ScreenEnum.SERVER_SCREEN)
//        ScreenManager.getInstance().hide(ScreenEnum.SERVER_SCREEN)
        ScreenManager.getInstance().show(ScreenEnum.GAME)
    }
    override fun dispose() {
        ScreenManager.getInstance().dispose()
        CRAssetManager.getInstance().dispose()
    }
}
