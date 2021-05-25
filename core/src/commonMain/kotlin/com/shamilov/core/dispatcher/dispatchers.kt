package com.shamilov.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Shamilov on 21.05.2021
 */

internal expect val dispatcherUI: CoroutineDispatcher
internal expect val dispatcherIO: CoroutineDispatcher