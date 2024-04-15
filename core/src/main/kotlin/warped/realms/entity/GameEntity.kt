package warped.realms.entity

import generated.systems.Factories
import generated.systems.Systems
import generated.systems.initEntity
import warped.realms.component.*

class GameEntity(
    imCmp: ImageComponent.() -> ImageComponent,
    anCmp: AnimationComponent.() -> AnimationComponent,
    trCmp: TransformComponent.() -> TransformComponent,
    phCmp: PhysicComponent.() -> PhysicComponent,
    mvCmp: MoveComponent.() -> MoveComponent,
    tileCmp: (TiledComponent.() -> TiledComponent)?,
    val systems: Systems,
    factories: Factories
) : Entity(factories) {
    init {
        addCmp<AnimationComponent>(anCmp)
        addCmp<ImageComponent>(imCmp)
        addCmp<MoveComponent>(mvCmp)
        addCmp<PhysicComponent>(phCmp)
        addCmp<TransformComponent>(trCmp)
        if (tileCmp != null) {
            addCmp<TiledComponent>(tileCmp)
        }
        initEntity(factories, systems)
    }

    fun addCollisionComponent(lambda: TiledComponent.() -> TiledComponent) {
        addCmp<TiledComponent>(lambda)
        initEntity(factories, systems)
    }
}
