package controller.router

import controller.handler.UserHandlerImpl
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Route.userRouting(){
    val handler = UserHandlerImpl()
    post("/sign-up"){
        handler.signUp(call)
    }
    post("/sign-in"){
        handler.signIn(call)
    }
    authenticate("jwt-auth") {
        get("/me"){ handler.getSelf(call) }
    }
}
