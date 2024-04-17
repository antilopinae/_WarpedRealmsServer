package warped.realms.system.update.mapper

import Update
import System
import dao.EntityDao
import generated.systems.Factories
import generated.systems.Systems
import warped.realms.entity.mapper.EntityMapper


@System
@Update(12)
class ServerMapperSystem(
    val systems: Systems,
    val factories: Factories
) {
    private val entitiesDao: MutableList<EntityDao> = mutableListOf()
    private val entityMappers = mutableListOf<EntityMapper>()

    fun GetEntitiesDao() = entitiesDao
    fun Update(deltaTime: Float) {
        entityMappers.forEach { it.Update() }
        entitiesDao.addAll( entityMappers.map { it.MapEntity() })
    }
    fun PutComponent(cmp: EntityMapper) {
        entityMappers.add(cmp)
    }
    fun Dispose() {
        entityMappers.clear()
    }

}
