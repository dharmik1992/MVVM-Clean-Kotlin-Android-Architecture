package com.mvvm.clean.domain.interactors

import com.mvvm.clean.domain.models.MovieDetail
import com.mvvm.clean.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

typealias GetMovieDetailsBaseUseCase = BaseUseCase<Int, Flow<MovieDetail>>

class GetMovieDetailsByIdUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    GetMovieDetailsBaseUseCase {

    override suspend fun invoke(params: Int): Flow<MovieDetail> {
        return movieRepository.getMovieDetails(params)
    }
}