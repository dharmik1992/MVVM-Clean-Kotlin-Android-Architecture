package com.mvvm.clean.domain.interactor

import com.mvvm.clean.domain.fakes.FakeMovieListData
import com.mvvm.clean.domain.interactors.GetMovieDetailsByIdUseCase
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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailByIdTestDomain : DomainBaseTest() {

    @Mock
    lateinit var movieRepository: MovieRepository

    lateinit var movieDetailsByIdUseCase: GetMovieDetailsByIdUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieDetailsByIdUseCase = GetMovieDetailsByIdUseCase(movieRepository)
    }

    @Test
    fun `get movie details should return success response with movie data`() =
        dispatcher.runBlockingTest {
            //Arrange
            val movieId = 100
            val movieDetailFlow = flow { emit(FakeMovieListData.getMovieDetail()) }
            whenever(movieRepository.getMovieDetails(movieId)) doReturn movieDetailFlow

            //Act
            val movieDetail = movieDetailsByIdUseCase.invoke(movieId).single()

            //Assert
            assertEquals(movieDetail.id, movieId)
            verify(movieRepository, times(1)).getMovieDetails(movieId)
        }

    @Test
    fun `get movie details should return error with exception`() = dispatcher.runBlockingTest {
        //Arrange
        val movieId = 100
        whenever(movieRepository.getMovieDetails(movieId)) doAnswer { throw IOException() }

        //Act
        launch(exceptionHandler) { movieDetailsByIdUseCase.invoke(movieId).single() }

        //Assert
        assertThat(exceptionHandler.uncaughtExceptions.first(), CoreMatchers.instanceOf(IOException::class.java))
        verify(movieRepository, times(1)).getMovieDetails(movieId)
    }
}