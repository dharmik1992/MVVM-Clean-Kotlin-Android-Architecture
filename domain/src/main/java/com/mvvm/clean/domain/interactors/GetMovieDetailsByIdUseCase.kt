package com.mvvm.clean.domain.interactors

import com.mvvm.clean.domain.models.MovieDetail
import com.mvvm.clean.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

typealias GetMovieDetailsBaseUseCase = BaseUseCase<Int, Flow<MovieDetail>>

/**
 * use case to get movie details based on movie Id
 *
 * @property movieRepository repository instance
 */
class GetMovieDetailsByIdUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    GetMovieDetailsBaseUseCase {

    /**
     * Function returns a Movie detail object
     *
     * @param params movie id
     * @return movie detail
     */
    override suspend fun invoke(params: Int) = movieRepository.getMovieDetails(params)

}