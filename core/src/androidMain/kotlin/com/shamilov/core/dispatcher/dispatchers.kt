package com.shamilov.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Shamilov on 21.05.2021
 */

internal actual val dispatcherUI: CoroutineDispatcher
    get() = Dispatchers.Main

internal actual val dispatcherIO: CoroutineDispatcher
    get() = Dispatchers.IO