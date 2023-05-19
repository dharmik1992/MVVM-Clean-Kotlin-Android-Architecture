package com.mvvm.clean.data.source

import com.mvvm.clean.data.fakes.FakeMovieListData
import com.mvvm.clean.data.repository.MovieRemote
import com.mvvm.clean.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieRemoteDataSourceTest : DataBaseTest() {

    @Mock
    lateinit var movieRemote: MovieRemote

    lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @Before
    fun setup() {
        movieRemoteDataSource = MovieRemoteDataSource(movieRemote)
    }

    @Test
    fun `get popular movie should return movies from remote`() = dispatcher.runBlockingTest {
        //Arrange
        whenever(movieRemote.getPopularMovies()) doReturn FakeMovieListData.getMovieList(2)

        //Act
        val movies = movieRemoteDataSource.getPopularMovies()

        //Assert
        TestCase.assertEquals(movies.size, 2)
        verify(movieRemote, times(1)).getPopularMovies()
    }

    @Test
    fun `get popular movie should return error`() = dispatcher.runBlockingTest {
        //Arrange
        whenever(movieRemote.getPopularMovies()) doAnswer { throw IOException() }

        //Act
        launch(exceptionHandler) { movieRemoteDataSource.getPopularMovies() }

        //Assert
        MatcherAssert.assertThat(
            exceptionHandler.uncaughtExceptions.first(),
            CoreMatchers.instanceOf(IOException::class.java)
        )
        verify(movieRemote, times(1)).getPopularMovies()
    }

    @Test
    fun `save movies in cache should return error - not supported by remote`() =
        dispatcher.runBlockingTest {

            // Act (When)
            launch(exceptionHandler) {
                movieRemoteDataSource.saveMoviesInCache(
                    FakeMovieListData.getMovieList(
                        2
                    )
                )
            }

            // Assert (Then)
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(UnsupportedOperationException::class.java)
            )
        }

    @Test
    fun `is cached should return error - not supported by remote`() =
        dispatcher.runBlockingTest {

            // Act (When)
            launch(exceptionHandler) { movieRemoteDataSource.isCached() }

            // Assert (Then)
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(UnsupportedOperationException::class.java)
            )
        }
}