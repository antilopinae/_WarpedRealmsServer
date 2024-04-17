package warped.realms.dao.gamepackage

import dao.Entity
import dao.EntityDao
import dao.GamePackage
import warped.realms.dao.entitydao.EntityDaoMapper

class GamePackageDismapper {
    fun DismapGamePackage(gamePackage: GamePackage): List<EntityDao>{
        return gamePackage.entities
    }
    fun MapGamePackage(entities: List<EntityDao>): GamePackage {
        return GamePackage().also { it.entities.addAll(entities) }
    }
    fun MapGamePackage(gamePackage: GamePackage, entities: List<EntityDao>): GamePackage {
        return gamePackage.also {
            it.entities.clear()
            it.entities.addAll(entities)
        }
    }
}
