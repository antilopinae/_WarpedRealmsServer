package plugins

import controller.router.userRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() = routing {
    route("/users"){
        userRouting()
    }
    route("/status"){
        get {
            call.respondText("Service is alive)")
        }
    }
}
