package com.mvvm.clean.app.presentation.screen.home

import com.mvvm.clean.app.presentation.utils.CoroutineContextProvider
import com.mvvm.clean.app.presentation.utils.ExceptionHandler
import com.mvvm.clean.app.presentation.viewModel.BaseViewModel
import com.mvvm.clean.domain.interactors.GetPopularMovieListUseCase
import com.mvvm.clean.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject

sealed class MovieUIStateModel {
    object Loading : MovieUIStateModel()
    data class Error(val error: Throwable) : MovieUIStateModel()
    data class Success(val data: List<Movie>) : MovieUIStateModel()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val movieListUseCase: GetPopularMovieListUseCase
) : BaseViewModel(coroutineContextProvider) {

    private val _popularMovieList = MutableStateFlow<MovieUIStateModel>(MovieUIStateModel.Loading)
    var popularMovieList: StateFlow<MovieUIStateModel> = _popularMovieList


    override val coroutineExceptionHandler = CoroutineExceptionHandler { _ ,exception ->
        _popularMovieList.value = MovieUIStateModel.Error(exception)
    }

    fun getPopularMovies() {
        _popularMovieList.value = MovieUIStateModel.Loading
        launchCoroutineIO {
            movieListUseCase.invoke(Unit).collect() {
                _popularMovieList.value = MovieUIStateModel.Success(it)
            }
        }
    }

}