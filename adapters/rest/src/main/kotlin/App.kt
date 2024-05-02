package adapters.rest

import adapters.rest.config.configureDatabase
import adapters.rest.plugins.*
import io.ktor.server.application.*

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
