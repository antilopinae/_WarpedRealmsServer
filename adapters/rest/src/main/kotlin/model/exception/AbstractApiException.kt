package model.exception

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.ktor.http.*
import model.dto.Response

@JsonIgnoreProperties("cause", "stackTrace", "suppressed", "localizedMessage")
abstract class AbstractApiException (
    val status: HttpStatusCode,
    override val message: String
): Exception(), Response
