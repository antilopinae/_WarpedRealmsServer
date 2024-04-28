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
    private val entityMappers = mutableListOf<EntityMapper>()
    private val entitiesDao: MutableList<EntityDao> = mutableListOf()

    val spawnEntityEvent: EntitySpawnEvent by lazy {
        EntitySpawnEvent(entityType.PLAYER, injectSys<SpawnSystem>(systems))
    }
    val despawnEntityEvent: EntityDespawnEvent by lazy {
        EntityDespawnEvent(entityType.PLAYER, injectSys<SpawnSystem>(systems))
    }
    fun PutComponent(cmp: EntityMapper) {
        entityMappers.add(cmp)
    }
    fun DismapEntities(entities: List<EntityDao>){
        if(entities.isNotEmpty()) {
            this.entitiesDao.clear()
            entitiesDao.addAll(entities)
        }
//        else{
//            entitiesDao.add(EntityDao().apply {
//                this.id = 0
//                this.input_x = 0f
//                this.input_y = 0f
//                this.input_z = 0f
//                this.mouse_x = 0f
//                this.mouse_y = 0f
//            })
//        }
    }
    fun Update(delta: Float) {
        for(i in 0..entitiesDao.size-1){
            if(entityMappers.size>i){
                entityMappers[i].DismapEntity(entitiesDao[i])
                println("[DISMAPPER SYSTEM] Dismap entities input_x: ${entitiesDao[i].input_x}")
            }
            else{
                if(i<1){
                    spawnEntityEvent.onTick()
                    entityMappers[i].DismapEntity(entitiesDao[i])
                    println("spawn and dismap new entity")
                }
            }
        }
    }
    fun Dispose() {
        entityMappers.clear()
    }
}
