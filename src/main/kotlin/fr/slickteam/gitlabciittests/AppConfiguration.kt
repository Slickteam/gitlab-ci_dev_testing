package fr.slickteam.gitlabciittests

import com.natpryce.konfig.*
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import java.io.File

class AppConfiguration {

    companion object Configuration {
        val server_port = Key("server.port", intType)
        val server_host = Key("server.host", stringType)
        val mongo_host = Key("db.mongo.host", stringType)
        val mongo_port = Key("db.mongo.port", intType)
        val mongo_database = Key("db.mongo.database", stringType)
        val mongo_user = Key("db.mongo.user", stringType)
        val mongo_password = Key("db.mongo.password", stringType)

        val config = systemProperties() overriding
                EnvironmentVariables() overriding
                ConfigurationProperties.fromOptionalFile(File("dev.properties")) overriding
                ConfigurationProperties.fromResource("defaults.properties")

        fun getServerHost(): String {
            return config[server_host]
        }

        fun getServerPort(): Int {
            return config[server_port]
        }

        fun getMongoHost(): String {
            return config[mongo_host]
        }

        fun getMongoPort(): Int {
            return config[mongo_port]
        }

        fun getMongoDatabase(): String {
            return config[mongo_database]
        }

        fun getMongoUser(): String {
            return config[mongo_user]
        }

        fun getMongoPassword(): String {
            return config[mongo_password]
        }
    }

}
