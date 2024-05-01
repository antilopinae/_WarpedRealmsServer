package controller.handler

import model.dto.AuthRequest
import impl.UserServiceImpl
import utils.getUserId
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UserHandlerImpl {
    val service = UserServiceImpl()
    suspend fun signUp(call: ApplicationCall){
        val request = call.receive<AuthRequest>()
        val response = service.signUp(request)
        call.respond(response)
    }
    suspend fun signIn(call: ApplicationCall){
        val request = call.receive<AuthRequest>()
        val response = service.signIn(request)
        call.respond(response)
    }
    suspend fun getSelf(call: ApplicationCall){
        val id = call.getUserId()
        val response = service.findById(id)
        call.respond(response)
    }
}
