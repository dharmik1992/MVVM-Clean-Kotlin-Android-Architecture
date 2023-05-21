package com.mvvm.clean.app.presentation.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Dispatcher provider Interface
 */
interface CoroutineContextProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}

/**
 * Implementation class of Dispatcher provider
 */
class CoroutineContextProviderImpl @Inject constructor() : CoroutineContextProvider {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val default = Dispatchers.Default
}