package com.mvvm.clean.app.fakes

import com.mvvm.clean.app.fakes.FakeValueCreators.randomBoolean
import com.mvvm.clean.app.fakes.FakeValueCreators.randomDouble
import com.mvvm.clean.app.fakes.FakeValueCreators.randomInt
import com.mvvm.clean.app.fakes.FakeValueCreators.randomString
import com.mvvm.clean.domain.models.Movie
import com.mvvm.clean.domain.models.MovieDetail

object FakeMovieListData {

    fun getMovies(size: Int): List<Movie> {
        val movieList = mutableListOf<Movie>()
        repeat(size) {
            movieList.add(createMovieObject())
        }
        return movieList
    }

    private fun createMovieObject(): Movie {
        return Movie(
            adult = randomBoolean(),
            movieId = randomInt(),
            overview = randomString(),
            poster_path = randomString(),
            release_date = randomString(),
            vote_count = randomInt(),
            vote_average = randomDouble(),
            original_title = randomString(),
            title = randomString(),
            backdrop_path = randomString(),
            original_language = randomString(),
            popularity = randomDouble(),
            video = randomBoolean()
        )
    }

    fun getMovieDetail() : MovieDetail{
        return return MovieDetail(
            id = randomInt(),
            overview = randomString(),
            poster_path = randomString(),
            release_date = randomString(),
            vote_count = randomInt(),
            vote_average = randomDouble(),
            original_title = randomString(),
            title = randomString(),
            backdrop_path = randomString(),
            original_language = randomString(),
            popularity = randomDouble(),
            tagline = randomString(),
            status = randomString(),
            runtime = randomInt()
        )
    }

}