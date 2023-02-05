package io.tatumalenko

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.util.concurrent.Executors

object ConcurrencyKotlin {
    fun run() = runBlocking {
        val text = downloadTextWithRetry(3)
        println(text)
    }

    fun run2() = runBlocking {
        launch(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {
            val text = downloadTextWithRetry(3)
            println(text)
        }
    }

    private suspend fun downloadTextWithRetry(retryCount: Int): String {
        repeat(retryCount - 1) {
            try {
                return downloadText()
            } catch (e: IOException) {
                // ignore or log failed trial
            }
        }
        return downloadText() // last one trial
    }

    private suspend fun downloadText(): String {
        delay(1000)
        return "Hey!"
    }
}
