package warped.realms.server.mapper

import adapters.grpc.dao.RequestMessage
import adapters.grpc.dao.ResponseMessage
import adapters.grpc.server.dao.Observer
import dao.Entity
import warped.realms.dao.entitydao.EntityDaoMapper
import dao.GamePackage
import warped.realms.dao.gamepackage.GamePackageMapper
import java.util.concurrent.ConcurrentLinkedQueue

class ServerGamePackageController(
    val queue_response: ConcurrentLinkedQueue<Pair<Observer, ResponseMessage>>,
    val queue_request: ConcurrentLinkedQueue<Pair<Observer, RequestMessage>>
) {
    private val gamePackageMapper = GamePackageMapper()
    private val entityDaoMapper = EntityDaoMapper()

    private val observers: MutableList<Observer> = mutableListOf()
    private val packages: MutableList<GamePackage> = mutableListOf() //cashes of game states
    private val responses: MutableList<Pair<Observer, ResponseMessage>> = mutableListOf()
    private val requests: MutableList<Pair<Observer, RequestMessage>> = mutableListOf()

    fun Update(): GamePackage{
        sortRequests()
        updateRequests()
        val requests = mapRequests(requests)
        val entities = requests.map { (indObserver, request) ->
            Entity(indObserver) to entityDaoMapper.MapEntity(request)
        }
        // also need to save it
        return gamePackageMapper.MapGamePackage(entities)
    }
    fun SendGamePackage(gamePackage: GamePackage){
        val response = gamePackageMapper.UnmapGamePackage(gamePackage)
        responses.clear()
        responses.addAll(
            response.map{(entity, entityDao) ->
                observers[entity.index] to entityDaoMapper.UnmapEntity(entityDao)
            }
        )
        queue_response.addAll(responses)
    }
    private fun sortRequests(){
        requests.clear()
    }
    private fun updateRequests(){
        while(queue_request.isNotEmpty()){
            requests.add(queue_request.poll())
        }
    }
    private fun mapRequests(requests: List<Pair<Observer, RequestMessage>>): List<Pair<Int, RequestMessage>>{
        return requests.map { (observer,  request) ->
            if(!observers.contains(observer)){
                observers.add(observer)
            }
            observers.indexOf(observer)to request
        }
    }
}
