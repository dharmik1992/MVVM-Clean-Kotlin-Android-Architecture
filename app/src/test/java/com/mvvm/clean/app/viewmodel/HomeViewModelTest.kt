package com.mvvm.clean.app.viewmodel

import com.mvvm.clean.app.fakes.FakeMovieListData.getMovies
import com.mvvm.clean.app.presentation.viewModel.HomeViewModel
import com.mvvm.clean.app.presentation.viewModel.MovieUIStateModel
import com.mvvm.clean.app.utils.PresentationBaseTest
import com.mvvm.clean.domain.interactors.GetPopularMovieListUseCase
import com.mvvm.clean.domain.models.Movie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : PresentationBaseTest() {

    @Mock
    lateinit var movieListUseCase: GetPopularMovieListUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        homeViewModel = HomeViewModel(dispatcher, movieListUseCase)
    }

    @Test
    fun `getPopularMovies and update state with success`() =
        dispatcher.test.runBlockingTest {
            // Arrange
            val movieList = getMovies(5)
            val successState = MovieUIStateModel.Success(movieList)
            val movieflow: Flow<List<Movie>> = flow { emit(movieList) }
            `when`(movieListUseCase.invoke(Unit)).thenReturn(movieflow)

            // Act
            homeViewModel.getPopularMovies()

            // Advance the coroutine to ensure the flow has completed
            advanceUntilIdle()

            // Assert
            assertEquals(successState, homeViewModel.popularMovieList.value)
        }

    @Test
    fun `getPopularMovies and update state with error`() =
        dispatcher.test.runBlockingTest {
            // Arrange
            val error = Exception("Failed to fetch movie list")
            val errorState = MovieUIStateModel.Error(error)
            val movieFlow: Flow<List<Movie>> = flow { throw error }
            `when`(movieListUseCase.invoke(Unit)).thenReturn(movieFlow)

            // Act
            homeViewModel.getPopularMovies()

            // Advance the coroutine to ensure the flow has completed
            advanceUntilIdle()

            // Assert
            assertEquals(errorState, homeViewModel.popularMovieList.value)
        }

    @Test
    fun `getPopularMovies and updates response with empty list`() =
        dispatcher.test.runBlockingTest {
            // Arrange
            val emptyList = emptyList<Movie>()
            val successState = MovieUIStateModel.Success(emptyList)
            val movieFlow: Flow<List<Movie>> = flow { emit(emptyList) }
            `when`(movieListUseCase.invoke(Unit)).thenReturn(movieFlow)

            // Act
            homeViewModel.getPopularMovies()

            // Advance the coroutine to ensure the flow has completed
            advanceUntilIdle()
            //Assert
            assertEquals(successState, homeViewModel.popularMovieList.value)
        }
}