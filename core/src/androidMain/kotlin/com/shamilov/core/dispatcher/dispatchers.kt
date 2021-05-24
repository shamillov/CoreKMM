package com.shamilov.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val dispatcherUI: CoroutineDispatcher
    get() = Dispatchers.Main

actual val dispatcherIO: CoroutineDispatcher
    get() = Dispatchers.IO