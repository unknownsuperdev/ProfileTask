package com.task.room.impl.di

import android.content.Context
import androidx.room.Room
import com.task.room.api.ElementDao
import com.task.room.impl.AppDatabase
import com.task.room.impl.DB_NAME
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class RoomModule {

    @Single
    fun provideAppDatabase(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DB_NAME
    ).build()


    @Single
    fun provideElementDao(db: AppDatabase): ElementDao = db.elementDao()
}
