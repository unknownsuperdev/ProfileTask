package com.task.profile

import android.app.Application
import com.task.dispatchers.provider.di.DispatchersModule
import com.task.network.impl.di.DataModule
import com.task.profile.di.ProfileModule
import com.task.room.impl.di.RoomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
    }
    private val modules = listOf(
        AppModule().module,
        DataModule().module,
        DispatchersModule().module,
        RoomModule().module,
        ProfileModule().module
    )
}