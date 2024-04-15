package dao.mapper

import dao.GamePackage
import dao.GamePackageDismapper
import entity.mapper.EntityDismapper
import generated.systems.Systems
import generated.systems.injectSys
import server_logic.server.ServerGameLogic
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
        val entitiesDismapper = serverMapperSystem.entitiesDismapper
        val gamePackage = gamePackageDismapper.MapGamePackage(entitiesDismapper)
        return gamePackage
    }
    fun SetPackage(gamePackage: GamePackage){
        val entities = gamePackageDismapper.DismapGamePackage(gamePackage)
        serverDismapperSystem.DismapEntities(entities)
    }
    private fun resolvePackages(main_package: GamePackage, local_package: GamePackage){
        // nothing to do
    }
}
