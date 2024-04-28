package warped.realms.event

import warped.realms.entity.GameEntity
import warped.realms.system.entityType
import java.util.concurrent.locks.ReentrantLock

class EntitySpawnEvent(val typeEntity: entityType, vararg handlers: IHandleEvent): Event
{
    override var handlers: Array<IHandleEvent> = arrayOf<IHandleEvent>(*handlers)
//    val lock = ReentrantLock()
}
