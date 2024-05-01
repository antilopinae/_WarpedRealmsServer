package utils

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

//receive JWTPrincipal
fun ApplicationCall.getUserId(): Int{
    val id = principal<JWTPrincipal>()?.payload?.getClaim("id") ?: throw Exception("Unauthorized")
    return id.asInt()
}
