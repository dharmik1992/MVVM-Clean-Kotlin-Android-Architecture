package com.mvvm.clean.app.di

import com.mvvm.clean.app.BuildConfig
import com.mvvm.clean.remote.api.APIService
import com.mvvm.clean.remote.api.ServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideAPIService(): APIService {
        return ServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_URL)
    }
}