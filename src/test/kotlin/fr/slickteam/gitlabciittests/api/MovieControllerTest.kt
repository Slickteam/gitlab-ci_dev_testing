package fr.slickteam.gitlabciittests.api

import fr.slickteam.gitlabciittests.dao.MovieDao
import fr.slickteam.gitlabciittests.entity.Movie
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import strikt.api.expectThat
import strikt.assertions.isA

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class MovieControllerTest {

    // mocks
    private val ctx = mockk<Context>(relaxed = true)
    private val movieDao = mockk<MovieDao>(relaxed = true)

    private val movieController = MovieController(movieDao)

    @Test
    fun `GET film by title`() {
        every { ctx.queryParam("title") } returns "Back to the future"
        val movie = Movie(title = "Back to the future", _id = "1", director = "Robert Zemeckis")
        every { movieDao.findByTitle(any()) } returns movie
        movieController.getMovieByTitle(ctx)
        expectThat(ctx.resultString()).isA<String>()
    }

    @Test
    fun `GET film by title without title in parameter`() {
        every { ctx.queryParam("title") } returns null
        Assertions.assertThrows(BadRequestResponse::class.java) {
            movieController.getMovieByTitle(ctx)
        }
    }

    @Test
    fun `GET film by title with empty title`() {
        every { ctx.queryParam("title") } returns ""
        Assertions.assertThrows(BadRequestResponse::class.java) {
            movieController.getMovieByTitle(ctx)
        }
    }
}