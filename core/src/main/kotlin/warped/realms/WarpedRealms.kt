import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import ktx.app.KtxGame
import ktx.app.KtxScreen
import warped.realms.resources.ResourceController
import warped.realms.screen.CRAssetManager
import warped.realms.screen.KeeperGame
import warped.realms.screen.ScreenEnum
import warped.realms.screen.ScreenManager
import warped.realms.server.ServerGameLogicBuilder
import java.lang.System
import java.util.concurrent.ConcurrentLinkedQueue

//Игровая логика, работа с server_connector
class WarpedRealms : KtxGame<KtxScreen>() {
    //private val camera: OrthographicCamera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    val keeperGame = KeeperGame.apply { this.game = this@WarpedRealms }

    private val queue_response: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>> = ConcurrentLinkedQueue()
    private val queue_request: ConcurrentLinkedQueue<ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>> = ConcurrentLinkedQueue()


    override fun create() {
        System.setProperty("io.ktor.development", "true")

        Gdx.app.logLevel = Application.LOG_DEBUG
//        val a = ResourceController.getInstance().GetAtlas().regions

        ScreenManager.getInstance().init(this)
        ScreenManager.getInstance().show(ScreenEnum.GAME)
        ScreenManager.getInstance().hide(ScreenEnum.GAME)

        ScreenManager.getInstance().show(ScreenEnum.SERVER_SCREEN)
    }
    fun build(){
        queue_response.add(ConcurrentLinkedQueue())
        queue_request.add(ConcurrentLinkedQueue())

        val server = Thread{
            val serverConnector = ServerConnector(queue_response, queue_request)
        }
        server.start()

        val game_logic = Thread{
            val game_logic_builder = ServerGameLogicBuilder(queue_response, queue_request)
            game_logic_builder.Start()
        }
        game_logic.start()
    }
    override fun dispose() {
        ScreenManager.getInstance().dispose()
        CRAssetManager.getInstance().dispose()
    }
}
