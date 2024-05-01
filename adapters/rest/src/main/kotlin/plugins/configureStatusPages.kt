package adapters.rest.plugins

import adapters.rest.model.exception.AbstractApiException
import adapters.rest.model.exception.InternalServerException
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

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
