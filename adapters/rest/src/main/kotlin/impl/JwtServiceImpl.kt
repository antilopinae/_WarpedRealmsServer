package impl

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import database.dao.User
import com.typesafe.config.ConfigFactory
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*

class JwtServiceImpl {
    val config = HoconApplicationConfig(ConfigFactory.load())
    //from jwt config
    //val issure = config.property("jwt.issuer").getString()
    //val audince = config.property("jwt.audience").getString()
    //val secret = config.property("jwt.secret").getString()
    private val issure = "jsjsjj"
    private val audince = "sjsjjs"
    private val secret = "hshsh"
    private fun algorithm() = Algorithm.HMAC256(secret)
    fun generate(user: User): String{
        return JWT
            .create()
            .withAudience(audince)
            .withIssuer(issure)
            .withClaim("id", user.id.value)
            .sign(algorithm())
    }
    fun validate(credential: JWTCredential): JWTPrincipal?{
        return if(credential.payload.getClaim("id").asInt()>0){
            JWTPrincipal(credential.payload)
        } else{
            null
        }
    }
    fun verifier(): JWTVerifier{
        return JWT
            .require(algorithm())
            .withAudience(audince)
            .withIssuer(issure)
            .build()
    }
}
