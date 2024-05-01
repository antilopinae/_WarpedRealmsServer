package config

import database.table.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

val config = HikariConfig().apply {
    jdbcUrl = "jdbc:mariadb://localhost:3306/warped_realms_user_db"
    driverClassName = "org.mariadb.jdbc.Driver"
    username = "admin"
    password = "s3cr3t"
    maximumPoolSize = 20
}

fun configureDatabase() {
    val source = HikariDataSource(config)
    Database.connect(source)
    transaction {
        SchemaUtils.create(Users)
    }
}
