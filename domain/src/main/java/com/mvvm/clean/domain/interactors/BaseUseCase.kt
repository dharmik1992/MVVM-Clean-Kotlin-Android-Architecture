package com.mvvm.clean.domain.interactors

interface BaseUseCase <in Parameter, out Result> {
    suspend operator fun invoke(params : Parameter) : Result
}