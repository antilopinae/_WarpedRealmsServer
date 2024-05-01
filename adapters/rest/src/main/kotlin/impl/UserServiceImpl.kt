package impl

import database.dao.User
import database.table.Users
import model.dto.AuthRequest
import model.dto.Response
import model.dto.TokenResponse
import model.dto.UserDto
import model.exception.AlreadyExistException
import model.exception.NotFoundException
import model.exception.UnauthorizedException
import org.jetbrains.exposed.sql.transactions.transaction

class UserServiceImpl {
    private val jwtService = JwtServiceImpl()
    private fun insecureHash(msg: String) = msg.hashCode().toString()

    fun signUp(request: AuthRequest): Response {
        val user = transaction {
            if (User.find { Users.email eq request.email }.empty()) {
                User.new {
                    email = request.email
                    password = insecureHash(request.password)
                }
            } else {
                throw AlreadyExistException()
            }
        }
        val token = jwtService.generate(user)
        return TokenResponse(token)
    }

    fun signIn(request: AuthRequest): Response {
        println(request)
        val user = transaction {
            val user = User.find{ Users.email eq request.email }.singleOrNull() ?: throw NotFoundException()
            if(user.password != insecureHash(request.password)){
                throw UnauthorizedException()
            }
            return@transaction user
        }
        val token = jwtService.generate(user)
        return TokenResponse(token)
    }
    fun findById(id: Int): UserDto {
        val user = transaction {
            User.findById(id) ?: throw NotFoundException()
        }
        return UserDto(id = user.id.value, email = user.email)
    }
}
