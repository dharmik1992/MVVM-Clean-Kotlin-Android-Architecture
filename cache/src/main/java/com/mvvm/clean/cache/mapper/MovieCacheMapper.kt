package com.mvvm.clean.cache.mapper

import com.mvvm.clean.cache.models.MovieCacheEntity
import com.mvvm.clean.data.models.MovieEntity
import javax.inject.Inject

class MovieCacheMapper @Inject constructor() : CacheMapper<MovieCacheEntity, MovieEntity> {
    override fun mapToCache(type: MovieEntity): MovieCacheEntity {
        return MovieCacheEntity(
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

    override fun mapFromCached(type: MovieCacheEntity): MovieEntity {
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