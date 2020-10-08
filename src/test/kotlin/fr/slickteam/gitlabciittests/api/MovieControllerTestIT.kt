package fr.slickteam.gitlabciittests.api

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import fr.slickteam.gitlabciittests.entity.Movie
import fr.slickteam.gitlabciittests.launchApp
import fr.slickteam.gitlabciittests.persistence.Persistence
import io.javalin.Javalin
import org.junit.jupiter.api.*
import org.litote.kmongo.getCollectionOfName
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isTrue


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Movie API")
class MovieControllerTestIT {

    private lateinit var app: Javalin

    @BeforeAll
    fun setUp() {
        app = launchApp()
        // Inject the base path to no have repeat the whole URL
        FuelManager.instance.basePath = "http://localhost:${app.port()}/"
        Persistence.database.getCollectionOfName<Movie>("movie")
            .insertOne(Movie(_id = "1", title = "Tenet", director = "Christopher Nolan"))
    }

    @AfterAll
    fun tearDown() {
        // Clean collection and stops the application when the tests have completed
        Persistence.database.getCollectionOfName<Movie>("movie").drop()
        app.stop()
    }

    @Test
    fun `Get movie collection`() {
        val (_, _, result) = "movie".httpGet().responseString()

        expectThat(result.get()).contains("[Movie(_id=1, title=Tenet, director=Christopher Nolan, releaseDate=null)]")
    }

    @Test
    fun `Find movie by title`() {
        val (_, _, result) = "movie/title?title=Tenet".httpGet().responseString()

        expectThat(result.get()).contains("Movie(_id=1, title=Tenet, director=Christopher Nolan, releaseDate=null)")
    }

    @Test
    fun `Find movie by title - with empty title`() {
        val (_, _, result) = "movie/title?title=".httpGet().responseString()
        val (_, error) = result

        expectThat(error!!.response.statusCode.equals(400)).isTrue()
    }

}