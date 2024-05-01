package adapters.rest.model.exception

import io.ktor.http.*

class InternalServerException: AbstractApiException(
    status = HttpStatusCode.InternalServerError,
    message = "Something went wrong"
)
