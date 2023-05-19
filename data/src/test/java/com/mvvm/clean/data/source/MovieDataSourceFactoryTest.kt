package com.mvvm.clean.data.source

import com.mvvm.clean.data.repository.MovieCache
import com.mvvm.clean.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDataSourceFactoryTest : DataBaseTest() {

    @Mock
    lateinit var movieCacheDataSource: MovieCacheDataSource

    @Mock
    lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @Mock
    lateinit var movieCache: MovieCache

    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    @Before
    fun setup() {
        movieDataSourceFactory =
            MovieDataSourceFactory(movieCacheDataSource, movieRemoteDataSource, movieCache)
    }

    @Test
    fun `get data store with cache should return movies from cache from cache data store`() =
        dispatcher.runBlockingTest {
            //Arrange
            val isCached = true
            whenever(movieCache.isExpired()) doReturn false

            //Act
            val dataSource = movieDataSourceFactory.getDataStore(isCached)

            //Assert
            assertThat(dataSource, instanceOf(MovieCacheDataSource::class.java))
            verify(movieCache, times(1)).isExpired()
        }

    @Test
    fun `get data store with cache should return movies from remote data source`() =
        dispatcher.runBlockingTest {
            //Arrange
            val isCached = true
            whenever(movieCache.isExpired()) doReturn true

            //Act
            val dataSource = movieDataSourceFactory.getDataStore(isCached)

            //Assert
            assertThat(dataSource, instanceOf(MovieRemoteDataSource::class.java))
            verify(movieCache, times(1)).isExpired()
        }
}