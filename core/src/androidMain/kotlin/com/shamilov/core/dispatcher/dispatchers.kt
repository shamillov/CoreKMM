package com.shamilov.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val dispatcherUI: CoroutineDispatcher
    get() = Dispatchers.Main

internal actual val dispatcherIO: CoroutineDispatcher
    get() = Dispatchers.IO