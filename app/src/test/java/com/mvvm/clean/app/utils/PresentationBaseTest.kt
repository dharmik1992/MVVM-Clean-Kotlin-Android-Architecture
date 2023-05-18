package com.mvvm.clean.app.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class PresentationBaseTest {

    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    val dispatcher = TestContextProvider()
}