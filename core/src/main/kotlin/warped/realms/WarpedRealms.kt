import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import generated.systems.Factories
import generated.systems.Systems
import ktx.app.KtxGame
import ktx.app.KtxScreen
import warped.realms.screen.Screen

//Игровая логика, работа с server_connector
class WarpedRealms : KtxGame<KtxScreen>() {
    //private val camera: OrthographicCamera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    val factories = Factories()
    val systems = Systems(factories)
    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        addScreen(Screen(this, systems))
        setScreen<Screen>()
    }
    override fun dispose() {
        super.dispose()
    }
}
