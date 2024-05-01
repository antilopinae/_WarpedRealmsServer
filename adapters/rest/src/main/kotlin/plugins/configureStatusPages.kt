package plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import model.exception.AbstractApiException
import model.exception.InternalServerException


fun Application.configureStatusPages(){
    install(StatusPages){
        //client-side exc
        exception<AbstractApiException> { call, cause ->
            call.respond(cause.status, cause)
        }
        //server-side exc
        exception<Throwable> { call, cause ->
            println(cause.stackTraceToString())
            val exc = InternalServerException()
            call.respond(exc.status, exc)
        }
    }
}
