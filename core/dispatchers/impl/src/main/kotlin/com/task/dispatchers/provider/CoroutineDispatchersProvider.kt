package com.task.dispatchers.provider

import com.task.dispatchers.api.DispatchersProvider
import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Single

@Single
class CoroutineDispatchersProvider : DispatchersProvider {
    override val main by lazy { Dispatchers.Main }
    override val io by lazy { Dispatchers.IO }
}
