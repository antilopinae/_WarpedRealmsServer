package warped.realms.event

import warped.realms.system.entityType

class EntityDespawnEvent(val typeEntity: entityType, vararg handlers: IHandleEvent): Event
{
    override var handlers: Array<IHandleEvent> = arrayOf<IHandleEvent>(*handlers)
}
