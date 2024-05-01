package adapters.rest.model.exception

import adapters.rest.model.dto.Response
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.ktor.http.*

@JsonIgnoreProperties("cause", "stackTrace", "suppressed", "localizedMessage")
abstract class AbstractApiException (
    val status: HttpStatusCode,
    override val message: String
): Exception(), Response
