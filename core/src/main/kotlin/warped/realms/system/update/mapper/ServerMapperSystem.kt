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
    var _id = 0
    fun Update(deltaTime: Float) {
        entityMappers.forEach { it.Update() }
        entitiesDao.clear()
        entitiesDao.addAll( entityMappers.map { entityMapper -> entityMapper.MapEntity() })
        if(entitiesDao.size > 0)
            println("[MAPPER SYSTEM] entitiesDao size: ${entitiesDao.size} element input_x: ${entitiesDao[0].input_x}, element position_x: ${entitiesDao[0].positions[0].pos_x}")
        else
            println("[MAPPER SYSTEM] entitiesDao size: 0")
    }
    fun PutComponent(cmp: EntityMapper) {
        entityMappers.add(cmp)
    }
    fun Dispose() {
        entityMappers.clear()
    }

}
