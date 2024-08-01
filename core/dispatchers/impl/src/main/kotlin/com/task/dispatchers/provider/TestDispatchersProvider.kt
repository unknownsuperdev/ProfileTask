package com.task.dispatchers.provider

import com.task.dispatchers.api.DispatchersProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlin.coroutines.CoroutineContext

class TestDispatchersProvider : DispatchersProvider {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testCoroutineDispatcher by lazy { StandardTestDispatcher() }

    override val main: CoroutineContext = testCoroutineDispatcher
    override val io: CoroutineContext = testCoroutineDispatcher
}
