package com.mvvm.clean.cache

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mvvm.clean.cache.fakes.FakeMovieListData
import com.mvvm.clean.cache.mapper.MovieCacheMapper
import com.mvvm.clean.cache.repository.MovieCacheImp
import com.mvvm.clean.cache.utils.CacheBaseTest
import com.mvvm.clean.cache.utils.CacheSharedPreferenceHelper
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class MovieCacheImpTest : CacheBaseTest() {

    private val movieCacheMapper = MovieCacheMapper()

    lateinit var preferenceHelper: CacheSharedPreferenceHelper

    lateinit var movieCacheImp: MovieCacheImp

    @Before
    override fun setup() {
        super.setup()
        preferenceHelper = CacheSharedPreferenceHelper(context)
        movieCacheImp = MovieCacheImp(cacheDao, movieCacheMapper, preferenceHelper)
    }

    @Test
    fun `get popular movie should return success with movies from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange
            val movieEntity = FakeMovieListData.getMovieEntityList(5)
            movieCacheImp.saveMoviesInCache(movieEntity)

            //Act
            val movies = movieCacheImp.getPopularMovies()

            //Assert

            assertEquals(movies.size, 5)
        }

    @Test
    fun `get popular movie should return success with empty movie list from local room cache`() =
        dispatcher.runBlockingTest {
            //Act
            val movies = movieCacheImp.getPopularMovies()

            //Assert
            assertTrue(movies.isEmpty())
        }

    @Test
    fun `is cached should return success true`() = dispatcher.runBlockingTest {
        // Arrange
        val movieEntity = FakeMovieListData.getMovieEntityList(1)
        movieCacheImp.saveMoviesInCache(movieEntity)

        // Act
        val isCached = movieCacheImp.isCached()

        // Assert
        assertTrue(isCached)
    }

    @Test
    fun `is cached should return success false`() = dispatcher.runBlockingTest {
        // Act
        val isCached = movieCacheImp.isCached()

        // Assert (Then)
        TestCase.assertFalse(isCached)
    }

    @Test
    fun `set last cached time should return saved time`() = dispatcher.runBlockingTest {
        // Arrange
        val time = 1000L

        // Act
        movieCacheImp.setLastCacheTime(time)
        val lastCacheTime = preferenceHelper.lastCacheTime

        // Assert
        assertEquals(lastCacheTime, lastCacheTime)
    }

    @Test
    fun `is expired cache time cached true`() = dispatcher.runBlockingTest {
        // Act
        val isExpired = movieCacheImp.isExpired()

        // Assert
        assertTrue(isExpired)
    }

    @Test
    fun `is expired cache time cached false`() = dispatcher.runBlockingTest {
        // Arrange
        val time = System.currentTimeMillis()
        movieCacheImp.setLastCacheTime(time)

        // Act
        val isExpired = movieCacheImp.isExpired()

        // Assert
        TestCase.assertFalse(isExpired)
    }
}