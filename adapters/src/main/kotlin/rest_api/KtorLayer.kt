package rest_api

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class KtorLayer {
    //server ktor connect
    init {
        build()
        println("Ktor started")
    }
    fun build(){
        val ktor = Thread{
            this.start()
        }
        ktor.start()
    }
    fun start(){
        embeddedServer(Netty, port = 8080) {
            module()
        }.start(wait = true)
    }
    fun Application.module() =
        routing {
            get("/"){
                call.respond("Hello world")
            }
        }
}
