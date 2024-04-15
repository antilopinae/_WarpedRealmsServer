package warped.realms.entity

import generated.systems.Factories
import generated.systems.createCmp
import kotlin.reflect.KClass

open class Entity(
    val factories: Factories
) {
    val cmps: MutableMap<KClass<*>, Any> = mutableMapOf()
    protected inline fun <reified T : Any> addCmp(noinline p: T.() -> T) {
        println("${T::class}")
        if (!cmps.equals(T::class)) cmps.put(T::class, createCmp<T>(factories, p))
    }
    inline fun <reified T> getCmp(): T {
        return (cmps.get(T::class) as T)!!
    }
}
