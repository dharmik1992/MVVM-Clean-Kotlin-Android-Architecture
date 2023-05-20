package com.mvvm.clean.remote.fakes

import com.mvvm.clean.remote.fakes.FakeValueFactory.randomBoolean
import com.mvvm.clean.remote.fakes.FakeValueFactory.randomDouble
import com.mvvm.clean.remote.fakes.FakeValueFactory.randomInt
import com.mvvm.clean.remote.fakes.FakeValueFactory.randomString
import com.mvvm.clean.remote.models.popularMovieModels.MovieDataModel
import com.mvvm.clean.remote.models.popularMovieModels.MovieModel

object FakeRemoteMovieData {

    fun getResponse(size: Int): MovieDataModel {
        return MovieDataModel(1, getMoviesModel(size), randomInt(), randomInt())
    }

    private fun getMoviesModel(size: Int): List<MovieModel> {
        val movieList = mutableListOf<MovieModel>()
        repeat(size) {
            movieList.add(createMovieObject())
        }
        return movieList
    }

    private fun createMovieObject(): MovieModel {
        return MovieModel(
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