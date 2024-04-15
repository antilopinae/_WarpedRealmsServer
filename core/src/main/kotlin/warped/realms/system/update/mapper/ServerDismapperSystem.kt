package warped.realms.system.update.mapper

import ServerConnector
import Update
import System
import dao.EntityDao
import dao.mapper.EntityMapperController
import entity.mapper.EntityDismapper
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
    private val entityMappers = mutableListOf<EntityMapper>()
    private var entitiesDismapper: List<EntityDismapper> = listOf()

    val spawnEntityEvent: EntitySpawnEvent by lazy {
        EntitySpawnEvent(entityType.PLAYER, injectSys<SpawnSystem>(systems))
    }
    val despawnEntityEvent: EntityDespawnEvent by lazy {
        EntityDespawnEvent(entityType.PLAYER, injectSys<SpawnSystem>(systems))
    }
    fun PutComponent(cmp: EntityMapper) {
        entityMappers.add(cmp)
    }
    fun DismapEntities(entities: List<EntityDismapper>){
        this.entitiesDismapper = entities
    }
    fun Update(delta: Float) {
        //uncommit
//        for(i in 0..entitiesDismapper.size-1){
//            if(entityMappers.size>i){
//                entityMappers[i].DismapEntity(entitiesDismapper[i])
//            }
//            else{
//                if(!spawnEntityEvent.lock.isLocked){
//                    spawnEntityEvent.lock.lock()
//                    spawnEntityEvent.onTick()
//                    if(!spawnEntityEvent.lock.isLocked){
//                        entityMappers[i].DismapEntity(entitiesDismapper[i])
//                        println("spawn and dismap new entity")
//                    }
//                }
//            }
//        }
    }
    fun Dispose() {
        entityMappers.clear()
    }
}
