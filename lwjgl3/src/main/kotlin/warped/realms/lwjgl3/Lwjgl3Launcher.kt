@file:JvmName("Lwjgl3Launcher")

package warped.realms.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import WarpedRealms
import com.badlogic.gdx.ApplicationListener
import warped.realms.ServerBuilder
import warped.realms.screen.ServerScreen

/** Launches the desktop (LWJGL3) application. */
fun main() {
    //start ktor and grpc
    val server = ServerBuilder().build()

    //start window mode
    // This handles macOS support and helps on Windows.
    if (StartupHelper.startNewJvmIfRequired())
      return
    Lwjgl3Application(WarpedRealms(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("WarpedRealms:LootLabyrinth")
        setWindowedMode(600, 400)
        //setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode())
        //setResizable(false)
        useVsync(false)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
