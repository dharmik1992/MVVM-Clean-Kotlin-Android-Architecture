package com.mvvm.clean.app.presentation.viewModel

import com.mvvm.clean.app.presentation.utils.CoroutineContextProvider
import com.mvvm.clean.domain.interactors.GetPopularMovieListUseCase
import com.mvvm.clean.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject

/**
 * Sealed interface to hold state of movie listing screen
 *
 */
sealed interface MovieUIStateModel {
    object Loading : MovieUIStateModel
    data class Error(val error: Throwable) : MovieUIStateModel
    data class Success(val data: List<Movie>) : MovieUIStateModel
}

/**
 * ViewModel of movie list screen
 *
 * @property movieListUseCase use case to get popular movies
 * @param coroutineContextProvider dispatcher
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val movieListUseCase: GetPopularMovieListUseCase
) : BaseViewModel(coroutineContextProvider) {

    private val _popularMovieList = MutableStateFlow<MovieUIStateModel>(MovieUIStateModel.Loading)
    var popularMovieList: StateFlow<MovieUIStateModel> = _popularMovieList


    /**
     * Exception handler to handle error received in coroutine scope
     */
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _popularMovieList.value = MovieUIStateModel.Error(exception)
    }

    /**
     * Function launches coroutine scope and requests movie list which is a flow object
     *
     */
    fun getPopularMovies() {
        _popularMovieList.value = MovieUIStateModel.Loading
        launchCoroutineIO {
            movieListUseCase(Unit).collect {
                _popularMovieList.value = MovieUIStateModel.Success(it)
            }
        }
    }
}