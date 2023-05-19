package com.mvvm.clean.remote

import com.mvvm.clean.remote.api.APIService
import com.mvvm.clean.remote.fakes.FakeRemoteMovieData
import com.mvvm.clean.remote.mapper.MovieEntityMapper
import com.mvvm.clean.remote.repository.MovieRemoteImp
import com.mvvm.clean.remote.utils.RemoteBaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieRemoteImpTest : RemoteBaseTest() {

    @Mock
    lateinit var apiService: APIService

    @Mock
    lateinit var movieEntityMapper: MovieEntityMapper

    private val apiKey = "8347874837483478374837"
    private lateinit var movieRemoteImp: MovieRemoteImp

    @Before
    fun setup() {
        movieRemoteImp = MovieRemoteImp(apiService, movieEntityMapper, apiKey)
    }

    @Test
    fun `get popular movies should return response from remote server`() =
        dispatcher.runBlockingTest {
            //Arrange
            val remoteResponse = FakeRemoteMovieData.getResponse(5)
            whenever(apiService.getPopularMovies(apiKey)) doReturn remoteResponse

            //Act
            val movies = movieRemoteImp.getPopularMovies()

            //Assert
            assertEquals(movies.size, 5)
            verify(movieEntityMapper, times(5)).mapFromModel(any())
        }

    @Test
    fun `get popular movies should return error from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange
            whenever(apiService.getPopularMovies(apiKey)) doAnswer { throw IOException() }

            // Act
            launch(exceptionHandler) { movieRemoteImp.getPopularMovies() }

            // Assert
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                CoreMatchers.instanceOf(IOException::class.java)
            )
            verify(apiService, Mockito.times(1)).getPopularMovies(apiKey)
        }
}