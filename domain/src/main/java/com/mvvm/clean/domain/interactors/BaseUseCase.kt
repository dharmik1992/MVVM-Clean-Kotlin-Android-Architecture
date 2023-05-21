package com.mvvm.clean.domain.interactors

/**
 * Base use case designed to implement command design pattern
 *
 * @param Parameter input parameter
 * @param Result Returns a result
 */
interface BaseUseCase <in Parameter, out Result> {
    suspend operator fun invoke(params : Parameter) : Result
}