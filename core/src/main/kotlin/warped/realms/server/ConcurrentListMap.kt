package warped.realms.server

import adapters.grpc.server.dao.Observer
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentMap

//TODO: Maybe in the future...
class ConcurrentListMap<T>(
    override val size: Int = 10,
    override val entries: MutableSet<MutableMap.MutableEntry<Observer, ConcurrentLinkedQueue<T>>> = mutableSetOf(),
    override val keys: MutableSet<Observer> = mutableSetOf<Observer>(),
    override val values: MutableCollection<ConcurrentLinkedQueue<T>> = mutableListOf()
) : ConcurrentMap<Observer, ConcurrentLinkedQueue<T>>
{
    override fun containsKey(key: Observer?): Boolean {
        if(key == null)
            return false
        return keys.contains(key)
    }

    override fun containsValue(value: ConcurrentLinkedQueue<T>?): Boolean {
        if(value == null)
            return false
        return values.contains(value)
    }

    override fun get(key: Observer?): ConcurrentLinkedQueue<T>? {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun remove(key: Observer?): ConcurrentLinkedQueue<T>? {
        TODO("Not yet implemented")
    }

    override fun remove(key: Observer, value: ConcurrentLinkedQueue<T>?): Boolean {
        TODO("Not yet implemented")
    }

    override fun putAll(from: Map<out Observer, ConcurrentLinkedQueue<T>>) {
        TODO("Not yet implemented")
    }

    override fun put(
        key: Observer?,
        value: ConcurrentLinkedQueue<T>?
    ): ConcurrentLinkedQueue<T>? {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun replace(
        key: Observer,
        value: ConcurrentLinkedQueue<T>
    ): ConcurrentLinkedQueue<T>? {
        TODO("Not yet implemented")
    }

    override fun replace(
        key: Observer,
        oldValue: ConcurrentLinkedQueue<T>,
        newValue: ConcurrentLinkedQueue<T>
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun putIfAbsent(
        key: Observer,
        value: ConcurrentLinkedQueue<T>?
    ): ConcurrentLinkedQueue<T>? {
        TODO("Not yet implemented")
    }

}
