package com.mvvm.clean.app.presentation.viewModel

import com.mvvm.clean.app.presentation.utils.CoroutineContextProvider
import com.mvvm.clean.domain.interactors.GetMovieDetailsByIdUseCase
import com.mvvm.clean.domain.models.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject

sealed interface MovieDetailUIStateModel {
    object Loading : MovieDetailUIStateModel
    data class Error(val error: Throwable) : MovieDetailUIStateModel
    data class Success(val data: MovieDetail) : MovieDetailUIStateModel
}

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val movieDetailsByIdUseCase: GetMovieDetailsByIdUseCase
) : BaseViewModel(coroutineContextProvider) {

    private val _movieDetail = MutableStateFlow<MovieDetailUIStateModel>(MovieDetailUIStateModel.Loading)
    var movieDetail: StateFlow<MovieDetailUIStateModel> = _movieDetail


    override val coroutineExceptionHandler = CoroutineExceptionHandler { _ ,exception ->
        _movieDetail.value = MovieDetailUIStateModel.Error(exception)
    }

    fun getMovieDetail(movieId: Int) {
        _movieDetail.value = MovieDetailUIStateModel.Loading
        launchCoroutineIO {
            movieDetailsByIdUseCase.invoke(movieId).collect() {
                _movieDetail.value = MovieDetailUIStateModel.Success(it)
            }
        }
    }
}