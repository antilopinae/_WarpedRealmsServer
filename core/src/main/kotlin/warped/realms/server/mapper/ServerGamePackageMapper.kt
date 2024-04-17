package warped.realms.server.mapper

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import dao.*
import warped.realms.dao.entitydao.EntityDaoMapper
import warped.realms.dao.gamepackage.GamePackageMapper

class ServerGamePackageMapper {
    private val gamePackageMapper = GamePackageMapper()
    private val entityDaoMapper = EntityDaoMapper()
    fun MapGamePackage(requests: List<RequestMessage>): GamePackage {
        return gamePackageMapper.MapGamePackage( requests.map {request -> Entity(0) to  entityDaoMapper.MapEntity(request, 0) } )
    }
    fun UnmapGamePackage(gamePackage: GamePackage): List<ResponseMessage>{
        val entities = gamePackageMapper.UnmapGamePackage(gamePackage)
        return entities.map { (entity, entityDao) ->
            entityDaoMapper.UnmapEntity(entityDao)
        }
    }
    fun ResolveGamePackage(main_package: GamePackage, local_package: GamePackage): List<Pair<Entity, EntityDao>>{
        // firstly return main
        return gamePackageMapper.UnmapGamePackage(main_package)
    }
}
