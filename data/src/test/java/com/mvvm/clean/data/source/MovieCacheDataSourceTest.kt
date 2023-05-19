package com.mvvm.clean.data.source

import com.mvvm.clean.data.fakes.FakeMovieListData
import com.mvvm.clean.data.repository.MovieCache
import com.mvvm.clean.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieCacheDataSourceTest : DataBaseTest() {

    @Mock
    lateinit var movieCache: MovieCache

    lateinit var movieCacheDataSource: MovieCacheDataSource

    @Before
    fun setup() {
        movieCacheDataSource = MovieCacheDataSource(movieCache)
    }

    @Test
    fun `get popular movie should return movies from local cache`() = dispatcher.runBlockingTest {
        //Arrange
        whenever(movieCache.getPopularMovies()) doReturn FakeMovieListData.getMovieList(2)

        //Act
        val movies = movieCacheDataSource.getPopularMovies()

        //Assert
        assertEquals(movies.size, 2)
        verify(movieCache, times(1)).getPopularMovies()
    }

    @Test
    fun `get popular movie should return error`() = dispatcher.runBlockingTest {
        //Arrange
        whenever(movieCache.getPopularMovies()) doAnswer { throw IOException() }

        //Act
        launch(exceptionHandler) { movieCacheDataSource.getPopularMovies() }

        //Assert
        assertThat(exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java))
        verify(movieCache, times(1)).getPopularMovies()
    }

    @Test
    fun `save movies passed movie list should save movies into local cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val movies = FakeMovieListData.getMovieList(2)

            // Act (When)
            movieCacheDataSource.saveMoviesInCache(movies)

            // Assert (Then)
            verify(movieCache, times(1)).saveMoviesInCache(movies)
            verify(movieCache, times(1)).setLastCacheTime(any())
        }

    @Test
    fun `save movies passed movie list should return error failed to save last time`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val movies = FakeMovieListData.getMovieList(2)
            whenever(movieCache.saveMoviesInCache(movies)) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { movieCacheDataSource.saveMoviesInCache(movies) }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(IOException::class.java)
            )
            verify(movieCache, times(1)).saveMoviesInCache(movies)
            verify(movieCache, times(0)).setLastCacheTime(any())
        }
}