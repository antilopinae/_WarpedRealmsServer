package server

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import com.grpc.HelloRequest
import com.grpc.HelloResponse
import dao.*

class ServerGamePackageMapper {
    private val gamePackageMapper = GamePackageMapper()
    private val entityDaoMapper = EntityDaoMapper()
    fun MapGamePackage(requests: List<RequestMessage>): GamePackage {
        return gamePackageMapper.MapGamePackage( requests.map { Entity(0) to  entityDaoMapper.MapEntity(it) } )
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
