package com.mvvm.clean.cache.utils

import android.content.Context
import android.content.SharedPreferences

open class CacheSharedPreferenceHelper(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = "com.demo.cache.preferences"
        private const val PREF_LAST_CACHE = "last_cache"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    var lastCacheTime: Long
        get() = preferences.getLong(PREF_LAST_CACHE, 0)
        set(value) = preferences.edit().putLong(PREF_LAST_CACHE, lastCacheTime).apply()
}