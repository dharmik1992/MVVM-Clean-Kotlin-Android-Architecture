package com.mvvm.clean.domain.interactors

import com.mvvm.clean.domain.models.Movie
import com.mvvm.clean.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetPopularMovieListBaseUseCase = BaseUseCase<Unit, Flow<List<Movie>>>

/**
 * use case to get popular movie list based on movie Id
 *
 * @property movieRepository repository instance
 */
class GetPopularMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : GetPopularMovieListBaseUseCase {

    /**
     * Function returns a Movie list object
     *
     * @param params unit
     * @return movie list
     */
    override suspend fun invoke(params: Unit) = movieRepository.getPopularMovies()

}