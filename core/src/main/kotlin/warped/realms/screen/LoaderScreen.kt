package warped.realms.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen

class LoaderScreen : KtxScreen {
    private var progressBarImg: Texture? = null
    private var progressBarBaseImg: Texture? = null
    private var batch: SpriteBatch? = null

    private fun loadAssets() {
//        CRAssetManager.getInstance().load("background.jpg", Texture::class.java)
//        CRAssetManager.getInstance().load("title.png", Texture::class.java)
//        CRAssetManager.getInstance().load("gameover.png", Texture::class.java)
//        CRAssetManager.getInstance().load("istrebitel1.png", Texture::class.java)
    }
    override fun show() {
        batch = SpriteBatch()
//        CRAssetManager.getInstance().load("progress_bar.png", Texture::class.java)
//        CRAssetManager.getInstance().load("progress_bar_base.png", Texture::class.java)
//        CRAssetManager.getInstance().finishLoading()
//        progressBarBaseImg = CRAssetManager.getInstance().get("progress_bar_base.png")
//        progressBarImg = CRAssetManager.getInstance().get("progress_bar.png")
        loadAssets()
    }
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(10f, 0.3f, 0.1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
//        batch!!.begin()
//        batch!!.draw(
//            progressBarBaseImg, 10f, (Gdx.graphics.height / 2 - progressBarBaseImg!!.height / 2).toFloat(),
//            (Gdx.graphics.width - 20).toFloat(), progressBarBaseImg!!.height.toFloat()
//        )
//        batch!!.draw(
//            progressBarImg, 10f, (Gdx.graphics.height / 2 - progressBarImg!!.height / 2).toFloat(),
//            (Gdx.graphics.width - 20) * CRAssetManager.getInstance().getProgress().toFloat(), progressBarImg!!.height.toFloat()
//        )
//        batch!!.end()
//
//        if (CRAssetManager.getInstance().update()) {
//            ScreenManager.getInstance().show(ScreenEnum.WELCOME_SCREEN)
//        }
    }
    override fun resize(width: Int, height: Int) {
    }
    override fun pause() {
    }
    override fun resume() {
    }
    override fun hide() {
    }
    override fun dispose() {
//        batch!!.dispose()
    }
}
