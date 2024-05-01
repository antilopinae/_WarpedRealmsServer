package adapters.rest.database.dao

import adapters.rest.database.table.Users
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<User>(Users)

    var email by Users.email
    var password by Users.password
}
