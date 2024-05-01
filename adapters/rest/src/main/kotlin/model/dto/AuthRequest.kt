package adapters.rest.model.dto

data class AuthRequest(
    val email: String,
    val password: String
)
