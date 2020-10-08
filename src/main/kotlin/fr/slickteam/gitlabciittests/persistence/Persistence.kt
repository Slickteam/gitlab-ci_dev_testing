package fr.slickteam.gitlabciittests.persistence

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
import fr.slickteam.gitlabciittests.AppConfiguration
import org.litote.kmongo.KMongo

class Persistence {

    companion object MongoConnection {
        var client: MongoClient =
            KMongo.createClient(
                MongoClientURI(
                    "mongodb://" +
                            AppConfiguration.getMongoUser() + ":" + AppConfiguration.getMongoPassword() +
                            "@" + AppConfiguration.getMongoHost() + ":" + AppConfiguration.getMongoPort() +
                            "/?authSource=" + AppConfiguration.getMongoDatabase()
                )
            )
        var database: MongoDatabase = client.getDatabase(AppConfiguration.getMongoDatabase())
    }
}
