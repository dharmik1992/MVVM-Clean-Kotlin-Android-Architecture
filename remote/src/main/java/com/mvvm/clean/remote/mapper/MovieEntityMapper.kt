package com.mvvm.clean.remote.mapper

import com.mvvm.clean.data.models.MovieEntity
import com.mvvm.clean.remote.models.MovieModel
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() : RemoteMapper<MovieModel, MovieEntity> {
    override fun mapFromModel(type: MovieModel): MovieEntity {
        return MovieEntity(
            adult = type.adult,
            movieId = type.movieId,
            overview = type.overview,
            poster_path = type.poster_path,
            release_date = type.release_date,
            vote_count = type.vote_count,
            vote_average = type.vote_average,
            original_title = type.original_title,
            title = type.title,
            backdrop_path = type.backdrop_path,
            original_language = type.original_language,
            popularity = type.popularity,
            video = type.video
        )
    }
}