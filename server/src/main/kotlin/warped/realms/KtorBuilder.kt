import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class KtorBuilder
{
    init {
        println("Ktor started")
    }
    fun start(){
        embeddedServer(Netty, port = 8080) {
            module()
        }.start(wait = true)
    }
}

fun Application.module() =
    routing {
        get("/"){
            call.respond("Hello world")
        }
    }
