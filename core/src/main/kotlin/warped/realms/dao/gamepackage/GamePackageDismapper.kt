package warped.realms.dao.gamepackage

import dao.GamePackage
import warped.realms.entity.EntityDismapper
import warped.realms.entity.mapper.EntityDismapperMapper

class GamePackageDismapper {
    val entityDismapperMapper = EntityDismapperMapper()
    fun DismapGamePackage(gamePackage: GamePackage): List<EntityDismapper>{
        TODO()
    }
    fun MapGamePackage(entities: List<EntityDismapper>): GamePackage {
        TODO()
    }
}
