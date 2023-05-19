package com.mvvm.clean.data

import com.mvvm.clean.data.fakes.FakeMovieListData
import com.mvvm.clean.data.mapper.MovieMapper
import com.mvvm.clean.data.repository.MovieDataSource
import com.mvvm.clean.data.source.MovieDataSourceFactory
import com.mvvm.clean.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImpTest : DataBaseTest() {

    @Mock
    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    @Mock
    lateinit var movieMapper: MovieMapper

    @Mock
    lateinit var movieDataSource: MovieDataSource

    lateinit var movieRepositoryImp: MovieRepositoryImp

    @Before
    fun setup() {
        movieRepositoryImp = MovieRepositoryImp(movieDataSourceFactory, movieMapper)
    }

    @Test
    fun `get popular movie with cached value true should return movie list from local cache and save the movies to the db`() =
        runBlockingTest {
            //Arrange
            val isCached = true
            whenever(movieDataSourceFactory.getCachedDataSource()) doReturn movieDataSource
            whenever(movieDataSource.isCached()) doReturn isCached
            whenever(movieDataSourceFactory.getDataStore(isCached)) doReturn movieDataSource
            whenever(
                movieDataSourceFactory.getDataStore(isCached).getPopularMovies()
            ) doReturn FakeMovieListData.getMovieList(3)

            //Act
            val movieList = movieRepositoryImp.getPopularMovies().single()

            //assert
            assertEquals(movieList.size, 3)
            verify(movieDataSourceFactory, times(2)).getCachedDataSource()
            verify(movieDataSource, times(1)).isCached()
            verify(movieDataSourceFactory, times(2)).getDataStore(isCached)
            verify(movieDataSourceFactory.getDataStore(isCached), times(1)).getPopularMovies()
            verify(movieMapper, times(3)).mapFromEntity(any())
            verify(movieMapper, times(3)).mapFromEntity(anyOrNull())
            verify(movieDataSourceFactory.getCachedDataSource(), times(1)).saveMoviesInCache(any())
        }

    @Test
    fun `get popular movies with cached value false should return movie list from remote`() = runBlockingTest {
        //Arrange
        val isCached = false
        whenever(movieDataSourceFactory.getCachedDataSource()) doReturn movieDataSource
        whenever(movieDataSource.isCached()) doReturn isCached
        whenever(movieDataSourceFactory.getDataStore(isCached)) doReturn movieDataSource
        whenever(
            movieDataSourceFactory.getDataStore(isCached).getPopularMovies()
        ) doReturn FakeMovieListData.getMovieList(3)

        //Act
        val movieList = movieRepositoryImp.getPopularMovies().single()

        //Assert
        assertEquals(movieList.size, 3)
        verify(movieDataSourceFactory, times(2)).getCachedDataSource()
        verify(movieDataSource, times(1)).isCached()
        verify(movieDataSourceFactory, times(2)).getDataStore(isCached)
        verify(movieDataSourceFactory.getDataStore(isCached), times(1)).getPopularMovies()
        verify(movieMapper, times(3)).mapFromEntity(any())
    }
}