package plugins

import impl.JwtServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureAuth(){
    val service = JwtServiceImpl()
    authentication {
        jwt("jwt-auth"){
            verifier(service.verifier())
            validate{
                jwtCredential -> service.validate(jwtCredential)
            }
            challenge{
                _, _ -> call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}
