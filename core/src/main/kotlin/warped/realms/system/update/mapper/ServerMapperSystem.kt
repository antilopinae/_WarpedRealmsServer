package warped.realms.system.update.mapper

import Update
import System
import warped.realms.entity.EntityDismapper
import generated.systems.Factories
import generated.systems.Systems
import warped.realms.entity.mapper.EntityMapper


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
