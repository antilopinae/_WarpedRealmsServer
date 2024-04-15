package dao

class GamePackageMapper {
    fun MapGamePackage(entities: List<Pair<Entity, EntityDao>>): GamePackage{
        TODO()
    }
    fun UnmapGamePackage(gamePackage: GamePackage): List<Pair<Entity, EntityDao>>{
        TODO()
    }
    fun ResolveGamePackage(main_package: GamePackage, local_package: GamePackage){
        TODO()
    }
}
