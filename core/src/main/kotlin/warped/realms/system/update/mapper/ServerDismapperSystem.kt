package warped.realms.system.update.mapper

import Update
import System
import dao.EntityDao
import generated.systems.Factories
import generated.systems.Systems
import generated.systems.injectSys
import warped.realms.entity.mapper.EntityMapper
import warped.realms.event.EntityDespawnEvent
import warped.realms.event.EntitySpawnEvent
import warped.realms.system.SpawnSystem
import warped.realms.system.entityType

@System
@Update(9)
class ServerDismapperSystem(
    val systems: Systems,
    val factories: Factories
) {
    private val entityMappers = mutableMapOf<Int, EntityMapper>()
    private val entitiesDao: MutableList<EntityDao> = mutableListOf()

    val spawnEntityEvent: EntitySpawnEvent by lazy {
        EntitySpawnEvent(entityType.PLAYER, injectSys<SpawnSystem>(systems))
    }
    val despawnEntityEvent: EntityDespawnEvent by lazy {
        EntityDespawnEvent(entityType.PLAYER, injectSys<SpawnSystem>(systems))
    }
    fun PutComponent(cmp: EntityMapper) {
        entityMappers[cmp.index] = cmp
    }
    fun DismapEntities(entities: List<EntityDao>){
        if(entities.isNotEmpty()) {
            this.entitiesDao.clear()
            entitiesDao.addAll(entities)
        }
    }
    fun Update(delta: Float) {
        var id = 0
        for(i in 0..entitiesDao.size-1){
            id = entitiesDao[i].id

            if(entityMappers[id] != null){
                entityMappers[id]!!.DismapEntity(entitiesDao[i])
                println("[SERVER DISMAPPER SYSTEM] {PLAYER: ${entitiesDao[i].id}} Dismap entities input_x: ${entitiesDao[i].input_x}")
            }
            else{
                spawnEntityEvent.also { it.Id = id }.onTick()
                entityMappers[id]!!.DismapEntity(entitiesDao[i])
                println("[SERVER DISMAPPER SYSTEM] {PLAYER: ${entitiesDao[i].id}} Spawn and Dismap new entity")
            }
        }
    }
    fun Dispose() {
        entityMappers.clear()
    }
}
