package warped.realms.server.gamelogic

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import dao.GamePackage
import warped.realms.server.mapper.ServerGamePackageController
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.locks.ReentrantLock

class GameLogicThread(
    val serverGameLogic: ServerGameLogic,
    val lockGameLogic: ReentrantLock,
    val queue_response: ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>,
    val queue_request: ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>
): Thread()
{
    private val serverGamePackageController = ServerGamePackageController(queue_response, queue_request)

    private var beginTime: Long = 0 // the time when the cycle begun
    private var timeDiff: Float = 0f // the time it took for the cycle to execute
    private var sleepTime: Float = 0f // ms to sleep (<0 if we're behind)
    private var framesSkipped: Int = 0 // number of frames being skipped

    fun GameLoop(){
        sleepTime = 0f
        while(lockGameLogic.isLocked) {
            beginTime = System.currentTimeMillis()
            framesSkipped = 0; // resetting the frames skipped
            //do some logic
            this.update(FRAME_PERIOD)
            timeDiff = (System.currentTimeMillis() - beginTime).toFloat()
            // calculate sleep time
            sleepTime = FRAME_PERIOD - timeDiff
            if (sleepTime > 0) {
                // if sleepTime > 0 we're OK
                try {
                    // send the thread to sleep for a short period
                    // very useful for battery saving
                    Thread.sleep((sleepTime).toLong())
                } catch (e: InterruptedException) {
                    println(e)
                }
            }
            try {
                while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS && lockGameLogic.isLocked) {
                    // we need to catch up
                    this.update(FRAME_PERIOD)  // update without rendering
                    sleepTime += FRAME_PERIOD // add frame period to check if in next frame
                    framesSkipped++
                }
            } finally {
            }
        }
    }
    override fun start() {
        super.start()
        serverGameLogic.Start()
        if(!lockGameLogic.isLocked)
            lockGameLogic.lock()
        GameLoop()
    }
    override fun interrupt() {
        if(lockGameLogic.isLocked)
            lockGameLogic.unlock()
        super.interrupt()
    }
    override fun clone(): GameLogicThread {
        super.clone()
        //something else
        return this
    }
    var game_package = GamePackage()
    private fun update(delta: Float){
        serverGamePackageController.Update(game_package)
        serverGameLogic.entityMapperController.SetPackage(game_package)
        serverGameLogic.Update(delta)
        game_package.apply{ serverGameLogic.entityMapperController.GetPackage(this) }
        serverGamePackageController.SendGamePackage(game_package)
    }
    companion object{
        const val MAX_FPS = 60
        const val MAX_FRAME_SKIPS = 4
        const val FRAME_PERIOD = 1000/ MAX_FPS.toFloat()
    }
}
