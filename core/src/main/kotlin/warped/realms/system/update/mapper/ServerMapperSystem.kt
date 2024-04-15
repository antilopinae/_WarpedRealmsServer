package warped.realms.system.update.mapper

import ServerConnector
import Update
import System
import entity.mapper.EntityDismapper
import generated.systems.Factories
import generated.systems.Systems
import warped.realms.entity.mapper.EntityMapper

import java.util.*


@System
@Update(12)
class ServerMapperSystem(
    val systems: Systems,
    val factories: Factories
) {
    val entitiesDismapper: MutableList<EntityDismapper> = mutableListOf()
    private val entityMappers = mutableListOf<EntityMapper>()

    fun Update(deltaTime: Float) {
        //uncommit
//        entityMappers.forEach { it.Update() }
//        entitiesDismapper.addAll( entityMappers.map { it.MapEntity() })
    }
    fun PutComponent(cmp: EntityMapper) {
        entityMappers.add(cmp)
    }
    fun Dispose() {
        entityMappers.clear()
    }

}
