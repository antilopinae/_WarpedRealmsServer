package warped.realms.dao.gamepackage

import dao.Entity
import dao.EntityDao
import dao.GamePackage

class GamePackageMapper {
    fun MapGamePackage(entities: List<Pair<Entity, EntityDao>>): GamePackage {
        return GamePackage().also { it.entities.addAll(entities.map { (entity, entityDao) -> entityDao }) }
    }
    fun UnmapGamePackage(gamePackage: GamePackage): List<Pair<Entity, EntityDao>>{
        return gamePackage.entities.map { entity -> (Entity(entity.id) to entity) }
    }
    fun ResolveGamePackage(main_package: GamePackage, local_package: GamePackage){
        TODO()
    }
}
