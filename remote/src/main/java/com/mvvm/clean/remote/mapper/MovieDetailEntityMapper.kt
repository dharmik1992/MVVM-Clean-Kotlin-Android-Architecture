package com.mvvm.clean.remote.mapper

import com.mvvm.clean.data.models.MovieDetailEntity
import com.mvvm.clean.remote.models.movieDetailModels.MovieDetailModel
import javax.inject.Inject

class MovieDetailEntityMapper @Inject constructor() :
    RemoteMapper<MovieDetailModel, MovieDetailEntity> {

    override fun mapFromModel(type: MovieDetailModel): MovieDetailEntity {
        return MovieDetailEntity(
            id = type.id,
            popularity = type.popularity,
            title = type.title,
            backdrop_path = type.backdrop_path,
            vote_count = type.vote_count,
            vote_average = type.vote_average,
            release_date = type.release_date,
            poster_path = type.poster_path,
            overview = type.overview,
            original_language = type.original_language,
            original_title = type.original_title,
            runtime = type.runtime,
            status = type.status,
            tagline = type.tagline
        )
    }
}