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
    var ids: MutableSet<Int> = mutableSetOf()
    fun SetPackage(gamePackage: GamePackage){
//        entities = gamePackageDismapper.DismapGamePackage(gamePackage)
        ids.clear()
        entities = gamePackage.entities.filter { entityDao ->
            val id = entityDao.id
            if(ids.contains(id))
            {
                throw Exception("ID Already used")
                //false
            }
            else
            {
                ids.add(entityDao.id)
                true
            }
        }
        serverDismapperSystem.DismapEntities(entities)
        entities.forEach {
            println("[EntMapperController] {PLAYER: ${it.id}} input_x: ${it.input_x}")
        }
        println("[EntMapperController] dismapEntities size: ${entities.size}")
    }
    private fun resolvePackages(main_package: GamePackage, local_package: GamePackage){
        // nothing to do
    }
}
