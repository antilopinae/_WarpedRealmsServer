package dao.mapper

import dao.EntityDao
import dao.GamePackage
import warped.realms.dao.gamepackage.GamePackageDismapper
import generated.systems.Systems
import generated.systems.injectSys
import warped.realms.system.update.mapper.ServerDismapperSystem
import warped.realms.system.update.mapper.ServerMapperSystem

class EntityMapperController(
    val systems: Systems
)
{
    private val gamePackageDismapper = GamePackageDismapper()

    val serverDismapperSystem: ServerDismapperSystem by lazy {
        injectSys<ServerDismapperSystem>(systems)
    }
    val serverMapperSystem: ServerMapperSystem by lazy {
        injectSys<ServerMapperSystem>(systems)
    }
    fun GetPackage(): GamePackage {
        val entitiesDao = serverMapperSystem.GetEntitiesDao()
        val gamePackage = gamePackageDismapper.MapGamePackage(entitiesDao)
        return gamePackage
    }
    fun GetPackage(gamePackage: GamePackage) {
        val entitiesDao = serverMapperSystem.GetEntitiesDao()
        gamePackage.apply { gamePackageDismapper.MapGamePackage(this, entitiesDao) }
    }
    lateinit var entities: List<EntityDao>
    fun SetPackage(gamePackage: GamePackage){
//        entities = gamePackageDismapper.DismapGamePackage(gamePackage)
        entities = gamePackage.entities
        serverDismapperSystem.DismapEntities(entities)
    }
    private fun resolvePackages(main_package: GamePackage, local_package: GamePackage){
        // nothing to do
    }
}
