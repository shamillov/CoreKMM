package com.shamilov.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

internal expect val dispatcherUI: CoroutineDispatcher
internal expect val dispatcherIO: CoroutineDispatcher