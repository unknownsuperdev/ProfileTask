package com.task.room.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.room.api.ElementDao
import com.task.room.entities.ProfileElementEntity

@Database(
    entities = [
        ProfileElementEntity::class
    ],
    version = DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun elementDao(): ElementDao
}
