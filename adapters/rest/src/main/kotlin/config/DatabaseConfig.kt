package adapters.rest.config

import adapters.rest.database.table.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

val config = HikariConfig().apply {
    jdbcUrl = "jdbc:mariadb://localhost:3306/test_db"
    driverClassName = "org.mariadb.jdbc.Driver"
    username = "antilopa"
    password = "antilopa"
    maximumPoolSize = 20
}

fun configureDatabase() {
    val source = HikariDataSource(config)
    Database.connect(source)
    transaction {
        SchemaUtils.create(Users)
    }
}
