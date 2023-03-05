package com.mvvm.clean.cache.utils

import androidx.room.migration.Migration

class Migrations {
    companion object {
        const val DB_VERSION = 1

        fun getMigration() : Array<Migration>{
            return arrayOf()
        }
    }
}