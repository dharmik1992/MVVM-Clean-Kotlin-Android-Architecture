package com.mvvm.clean.app.viewmodel

import com.mvvm.clean.app.fakes.FakeMovieListData
import com.mvvm.clean.app.fakes.FakeValueCreators
import com.mvvm.clean.app.presentation.viewModel.MovieDetailUIStateModel
import com.mvvm.clean.app.presentation.viewModel.MovieDetailViewModel
import com.mvvm.clean.app.utils.PresentationBaseTest
import com.mvvm.clean.domain.interactors.GetMovieDetailsByIdUseCase
import com.mvvm.clean.domain.models.MovieDetail
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest : PresentationBaseTest() {

    @Mock
    lateinit var movieDetailsByIdUseCase: GetMovieDetailsByIdUseCase

    lateinit var movieDetailViewModel: MovieDetailViewModel

    @Before
    fun setup() {
        movieDetailViewModel = MovieDetailViewModel(dispatcher, movieDetailsByIdUseCase)
    }

    @Test
    fun `get movie details by id should return success with movie detail`() = dispatcher.test.runBlockingTest{
        //Arrange
        val movieId = FakeValueCreators.randomInt()
        val movieDetail = FakeMovieListData.getMovieDetail()
        val successState = MovieDetailUIStateModel.Success(movieDetail)
        val movieDetailFlow : Flow<MovieDetail> = flow { emit(movieDetail) }
        whenever(movieDetailsByIdUseCase.invoke(movieId)).thenReturn(movieDetailFlow)

        //Act
        movieDetailViewModel.getMovieDetail(movieId)

        advanceUntilIdle()

        assertEquals(successState, movieDetailViewModel.movieDetail.value)

    }

    @Test
    fun `get movie details by id should return state with error`() =
        dispatcher.test.runBlockingTest {
            // Arrange
            val movieId = FakeValueCreators.randomInt()
            val error = Exception("Failed to fetch movie details")
            val errorState = MovieDetailUIStateModel.Error(error)
            val movieDetailFlow : Flow<MovieDetail> =flow { throw error }
            whenever(movieDetailsByIdUseCase.invoke(movieId)).thenReturn(movieDetailFlow)

            //Act
            movieDetailViewModel.getMovieDetail(movieId)

            // Advance the coroutine to ensure the flow has completed
            advanceUntilIdle()

            // Assert
            assertEquals(errorState, movieDetailViewModel.movieDetail.value)
        }
}