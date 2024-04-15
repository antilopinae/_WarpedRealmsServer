package warped.realms.screen

import WarpedRealms
import generated.systems.Systems
import ktx.app.KtxScreen

abstract class AScreen(
    val game: WarpedRealms,
    val systems: Systems
): KtxScreen {
}
