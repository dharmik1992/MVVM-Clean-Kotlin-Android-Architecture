package com.mvvm.clean.cache.mapper

interface CacheMapper<T, V> {

    fun mapToCache(type: V): T

    fun mapFromCached(type: T): V
}