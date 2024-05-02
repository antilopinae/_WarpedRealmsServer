import config.configureDatabase
import io.ktor.server.application.*
import plugins.*

//engine
fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

//app
fun Application.module() {
    configureDatabase()
    configureSerialization()
    configureAuth()
    configureStatusPages()
    configureRouting()
    configureTemplating()
}
