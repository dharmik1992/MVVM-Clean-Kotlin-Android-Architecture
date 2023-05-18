package com.mvvm.clean.domain.interactor

import com.mvvm.clean.domain.fakes.FakeMovieListData
import com.mvvm.clean.domain.interactors.GetPopularMovieListUseCase
import com.mvvm.clean.domain.repository.MovieRepository
import com.mvvm.clean.domain.utils.DomainBaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPopularMovieListTestDomain : DomainBaseTest() {

    @Mock
    lateinit var movieRepository: MovieRepository

    lateinit var movieListUseCase: GetPopularMovieListUseCase

    @Before
    fun setup() {
        movieListUseCase = GetPopularMovieListUseCase(movieRepository)
    }

    @Test
    fun `get popular movie should return success response with movie list`() =
        dispatcher.runBlockingTest {
            //Arrange
            val movieFlow = flow { emit(FakeMovieListData.getMovies(3)) }
            whenever(movieRepository.getPopularMovies()) doReturn movieFlow

            //Act
            val movieList = movieListUseCase.invoke(Unit).single()

            //Assert
            assertEquals(movieList.size, 3)
            verify(movieRepository, times(1)).getPopularMovies()
        }

    @Test
    fun `get popular movie should return error with exception`() = dispatcher.runBlockingTest {
        //Arrange
        whenever(movieRepository.getPopularMovies()) doAnswer { throw IOException() }

        //Act
        launch(exceptionHandler) { movieListUseCase.invoke(Unit).single() }

        //Assert
        assertThat(exceptionHandler.uncaughtExceptions.first(), CoreMatchers.instanceOf(IOException::class.java))
        verify(movieRepository, times(1)).getPopularMovies()
    }
}

