package com.nytimes.presentation.executor

import com.nytimes.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object JobExecutor : ThreadExecutor {

    private val threadPoolExecutor: ThreadPoolExecutor by lazy {
        ThreadPoolExecutor(
            3, 5, 10, TimeUnit.SECONDS,
            LinkedBlockingQueue(), JobThreadFactory()
        )
    }

    override fun execute(runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}