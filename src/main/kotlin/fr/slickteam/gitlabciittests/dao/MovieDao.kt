package fr.slickteam.gitlabciittests.dao

import fr.slickteam.gitlabciittests.entity.Movie
import fr.slickteam.gitlabciittests.persistence.Persistence
import org.litote.kmongo.aggregate
import org.litote.kmongo.eq
import org.litote.kmongo.getCollectionOfName

interface MovieDao {
    fun findByTitle(title: String): Movie?
    fun getMovieCollection(
        from: Int,
        limit: Int,
        orderBy: String,
        orderDir: String,
        filter: String
    ): Collection<Movie>
}

class MovieDaoImpl : MovieDao {
    private val movieCollection = Persistence.database.getCollectionOfName<Movie>("movie")

    override fun findByTitle(title: String): Movie? {
        return movieCollection.find(Movie::title eq title).first()
    }

    override fun getMovieCollection(
        from: Int,
        limit: Int,
        orderBy: String,
        orderDir: String,
        filter: String
    ): Collection<Movie> {
        return movieCollection.aggregate<Movie>(buildAggregateRequest(from, limit, orderBy, orderDir, filter))
                              .toCollection(linkedSetOf())
    }
}
