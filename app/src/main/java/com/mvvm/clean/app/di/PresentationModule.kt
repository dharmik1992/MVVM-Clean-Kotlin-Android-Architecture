package com.mvvm.clean.app.di

import com.mvvm.clean.app.presentation.utils.CoroutineContextProvider
import com.mvvm.clean.app.presentation.utils.CoroutineContextProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideCoroutineContextProvider(contextProvider: CoroutineContextProviderImpl): CoroutineContextProvider {
        return contextProvider
    }
}