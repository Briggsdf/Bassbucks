package io.codeworld.config

import com.fasterxml.jackson.databind.Module
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value
import org.ktorm.database.Database
import org.ktorm.jackson.KtormModule
import javax.inject.Singleton
import javax.sql.DataSource

@Factory
class BassbucksBeanFactory {

    @Value("\${data.dburl}")
    lateinit var dbUrl: String

    @Value("\${data.dbusername}")
    lateinit var dbUserName: String

    @Value("\${data.dbpassword}")
    lateinit var dbPassword: String

    @Bean
    fun database(): Database {
        return  Database.connect(url = dbUrl, user = dbUserName, password = dbPassword)
    }

    @Singleton
    fun ktormModule(): Module {
        return KtormModule()
    }
}