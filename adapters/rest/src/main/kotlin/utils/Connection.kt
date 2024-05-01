package utils

import io.ktor.websocket.*
import java.util.concurrent.atomic.AtomicInteger

//wrapper over session
class Connection(
    val session: DefaultWebSocketSession
) {
    val id: Int = lastId.getAndIncrement()
    companion object {
        val lastId = AtomicInteger(1)
    }
}
