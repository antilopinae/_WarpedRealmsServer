package warped.realms.system

import ktx.log.logger

object Logger {
    val logger = logger<Logger>()
}

fun Logger.debug(p: () -> String) {
    println(p.invoke())
//    Logger.logger.debug(p)
}

fun Logger.error(p: () -> String) {
    println(p.invoke())
//    Logger.logger.error(p)
}
