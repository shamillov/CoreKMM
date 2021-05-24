package com.shamilov.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.DISPATCH_QUEUE_PRIORITY_DEFAULT
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_global_queue
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal
import kotlin.native.concurrent.freeze

internal actual val dispatcherUI: CoroutineDispatcher
    get() = MainDispatcher

internal actual val dispatcherIO: CoroutineDispatcher
    get() = IoDispatcher

@ThreadLocal
private object MainDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) {
            try {
                block.run().freeze()
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}

@ThreadLocal
private object IoDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT.toLong(), 0.toULong())) {
            try {
                block.run().freeze()
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}