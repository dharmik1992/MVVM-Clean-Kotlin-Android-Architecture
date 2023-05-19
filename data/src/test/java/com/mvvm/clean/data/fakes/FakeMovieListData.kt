package com.mvvm.clean.data.fakes



import com.mvvm.clean.data.fakes.FakeValueCreators.randomBoolean
import com.mvvm.clean.data.fakes.FakeValueCreators.randomDouble
import com.mvvm.clean.data.fakes.FakeValueCreators.randomInt
import com.mvvm.clean.data.fakes.FakeValueCreators.randomString
import com.mvvm.clean.data.models.MovieEntity

object FakeMovieListData {

    fun getMovieList(size: Int): List<MovieEntity> {
        val movieList = mutableListOf<MovieEntity>()
        repeat(size) {
            movieList.add(createMovieObject())
        }
        return movieList
    }

    private fun createMovieObject(): MovieEntity {
        return MovieEntity(
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
}