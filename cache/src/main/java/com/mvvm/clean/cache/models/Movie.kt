package com.mvvm.clean.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mvvm.clean.cache.utils.CacheConstants


@Entity(tableName = CacheConstants.MOVIES_TABLE_NAME)
data class Movie(@PrimaryKey val id : Int, val name: String)
