package adapters.rest

import adapters.rest.config.configureDatabase
import adapters.rest.plugins.configureAuth
import adapters.rest.plugins.configureRouting
import adapters.rest.plugins.configureSerialization
import adapters.rest.plugins.configureStatusPages
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
}
