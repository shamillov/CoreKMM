package com.shamilov.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

expect val dispatcherUI: CoroutineDispatcher
expect val dispatcherIO: CoroutineDispatcher