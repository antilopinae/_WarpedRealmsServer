package adapters.rest.model.exception

import io.ktor.http.*

class NotFoundException: AbstractApiException(
    status = HttpStatusCode.NotFound,
    message = "Not found"
)
