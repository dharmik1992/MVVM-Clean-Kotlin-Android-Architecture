package com.mvvm.clean.app.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.clean.domain.interactors.GetPopularMovieListUseCase
import com.mvvm.clean.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieListUseCase: GetPopularMovieListUseCase
) : ViewModel() {

    private val _popularMovieList: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    var popularMovieList: StateFlow<List<Movie>> = _popularMovieList

    fun getPopularMovies() {
        viewModelScope.launch {
            movieListUseCase.invoke(Unit).collect() {
                _popularMovieList.value = it
            }
        }
    }
}