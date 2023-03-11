package com.mvvm.clean.app.di

import com.mvvm.clean.app.BuildConfig
import com.mvvm.clean.data.repository.MovieRemote
import com.mvvm.clean.remote.api.APIService
import com.mvvm.clean.remote.api.ServiceFactory
import com.mvvm.clean.remote.repository.MovieRemoteImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideAPIService(): APIService {
        return ServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideMovieRemote(movieRemoteImp: MovieRemoteImp) : MovieRemote{
        return movieRemoteImp
    }
}