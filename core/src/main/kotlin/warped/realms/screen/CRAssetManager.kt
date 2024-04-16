package warped.realms.screen

import com.badlogic.gdx.assets.AssetManager

class CRAssetManager : AssetManager() {
    fun init() {
    }
    @Synchronized
    override fun dispose() {
        super.dispose()
        instance = null
    }
    companion object {
        private var instance: CRAssetManager? = null
        fun getInstance(): CRAssetManager {
            if (instance == null) {
                instance = CRAssetManager()
            }
            return instance!!
        }
    }
}
