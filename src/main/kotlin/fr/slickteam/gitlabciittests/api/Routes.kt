package fr.slickteam.gitlabciittests.api

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

class Routes(
    private val movieController: MovieController
) {

    fun setApiRoutes(app: Javalin) {
        app.routes {
            path("movie") {

                get(movieController::getCollection)

                path("title") {
                    get(movieController::getMovieByTitle)
                }
            }

        }
    }
}

