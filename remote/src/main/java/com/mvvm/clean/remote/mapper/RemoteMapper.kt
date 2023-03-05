package com.mvvm.clean.remote.mapper

interface RemoteMapper<M, E> {

    fun mapFromModel(type : M) : E
}