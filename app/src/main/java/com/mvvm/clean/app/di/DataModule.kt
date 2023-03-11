package com.mvvm.clean.app.di

import com.mvvm.clean.data.MovieRepositoryImp
import com.mvvm.clean.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMovieRepository(movieRepositoryImp: MovieRepositoryImp): MovieRepository {
        return movieRepositoryImp
    }
}