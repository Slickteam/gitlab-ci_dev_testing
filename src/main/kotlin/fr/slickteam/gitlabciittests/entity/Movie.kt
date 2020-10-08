package fr.slickteam.gitlabciittests.entity

import java.time.Instant

@Suppress("ConstructorParameterNaming")
data class Movie(
    val _id: String? = null,
    val title: String,
    val director: String? = null,
    val releaseDate: Instant? = null) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (_id != other._id) return false
        if (title != other.title) return false
        if (director != other.director) return false

        return true
    }

}
