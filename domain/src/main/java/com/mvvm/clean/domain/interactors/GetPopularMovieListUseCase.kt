package com.mvvm.clean.domain.interactors

import com.mvvm.clean.domain.models.Movie
import com.mvvm.clean.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetPopularMovieListBaseUseCase = BaseUseCase<Unit, Flow<List<Movie>>>

class GetPopularMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : GetPopularMovieListBaseUseCase {

    override suspend fun invoke(params: Unit) = movieRepository.getPopularMovies()

}