package com.mvvm.clean.app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.clean.app.presentation.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Base ViewModel which contains some common functions
 *
 * @property contextProvider dispatcher provider
 */
abstract class BaseViewModel(private val contextProvider: CoroutineContextProvider) : ViewModel() {

    private val job: Job = Job()

    abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    /**
     * protected function which launches coroutine with IO dispatcher
     *
     * @param block higher order function to perform work in coroutine scope
     * @return Job object which returns a scope to perform coroutine work
     */
    protected fun launchCoroutineIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(contextProvider.io + job + coroutineExceptionHandler) {
            block()
        }
    }

    /**
     * protected function which launches coroutine with Main dispatcher
     *
     * @param block higher order function to perform work in coroutine scope
     * @return Job object which returns a scope to perform coroutine work
     */
    protected fun launchCoroutineMain(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(contextProvider.main + job + coroutineExceptionHandler) {
            block()
        }
    }

    /**
     * Function to clear job
     */
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}