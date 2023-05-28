package com.mvvm.clean.app.presentation.viewModel

import com.mvvm.clean.app.presentation.utils.CoroutineContextProvider
import com.mvvm.clean.domain.interactors.GetMovieDetailsByIdUseCase
import com.mvvm.clean.domain.models.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject

/**
 * Sealed interface to hold state of movie detail screen
 *
 */
sealed interface MovieDetailUIStateModel {
    object Loading : MovieDetailUIStateModel
    data class Error(val error: Throwable) : MovieDetailUIStateModel
    data class Success(val data: MovieDetail) : MovieDetailUIStateModel
}

/**
 * ViewModel of movie Detail screen
 *
 * @property movieDetailsByIdUseCase use case to get movie detail of particular movie ID
 * @param coroutineContextProvider dispatcher
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val movieDetailsByIdUseCase: GetMovieDetailsByIdUseCase
) : BaseViewModel(coroutineContextProvider) {

    private val _movieDetail =
        MutableStateFlow<MovieDetailUIStateModel>(MovieDetailUIStateModel.Loading)
    var movieDetail: StateFlow<MovieDetailUIStateModel> = _movieDetail

    /**
     * Exception handler to handle error received in coroutine scope
     */
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _movieDetail.value = MovieDetailUIStateModel.Error(exception)
    }

    /**
     * Function launches coroutine scope and requests movie details which is a flow object
     *
     * @param movieId movie id
     */
    fun getMovieDetail(movieId: Int) {
        _movieDetail.value = MovieDetailUIStateModel.Loading
        launchCoroutineIO {
            movieDetailsByIdUseCase(movieId).collect {
                _movieDetail.value = MovieDetailUIStateModel.Success(it)
            }
        }
    }
}