package fr.slickteam.gitlabciittests

import fr.slickteam.gitlabciittests.api.MovieController
import fr.slickteam.gitlabciittests.api.Routes
import fr.slickteam.gitlabciittests.dao.MovieDaoImpl
import io.javalin.Javalin

fun main(args: Array<String>) {
    launchApp()
}

fun launchApp(): Javalin {
    val app = Javalin.create().start(AppConfiguration.getServerPort())
    val routes = Routes(
        MovieController(MovieDaoImpl())
    )
    routes.setApiRoutes(app)
    return app
}