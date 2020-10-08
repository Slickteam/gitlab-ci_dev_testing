package fr.slickteam.gitlabciittests.api

import fr.slickteam.gitlabciittests.dao.MovieDao
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context

class MovieController(private val movieDao: MovieDao) {

    fun getCollection(ctx: Context) {
        val from = ctx.queryParam("from")?.toIntOrNull() ?: 0
        val limit = ctx.queryParam("limit")?.toIntOrNull() ?: 20
        val orderBy = ctx.queryParam("orderBy") ?: "id"
        val orderDir = if ("asc" == ctx.queryParam("orderDir")?.toLowerCase()) "asc" else "desc"
        val filter = ctx.queryParam("filter") ?: ""
        ctx.result(movieDao.getMovieCollection(from, limit, orderBy, orderDir, filter).toString())
    }

    fun getMovieByTitle(ctx: Context) {
        val title = ctx.queryParam("title")
        if (title.isNullOrBlank()) {
            throw BadRequestResponse()
        }
        ctx.result(movieDao.findByTitle(title).toString())
    }
}