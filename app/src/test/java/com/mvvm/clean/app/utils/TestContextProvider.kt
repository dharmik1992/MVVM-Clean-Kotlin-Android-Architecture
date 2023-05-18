package com.mvvm.clean.app.utils

import com.mvvm.clean.app.presentation.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class TestContextProvider : CoroutineContextProvider {
    val test = TestCoroutineDispatcher()

    override val io: CoroutineDispatcher = test

    override val main: CoroutineDispatcher = test

    override val default: CoroutineDispatcher = test
}