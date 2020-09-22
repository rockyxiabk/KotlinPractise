package com.rocky.core.download

import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class DownloadDispatcher private constructor() {
    private var mExecutorService: ExecutorService? = null
    private val runningTasks: Deque<DownloadTask> = ArrayDeque()

    @Synchronized
    fun executorService(): ExecutorService {
        if (mExecutorService == null) {
            mExecutorService = ThreadPoolExecutor(
                CORE_POOL_SIZE,
                Int.MAX_VALUE,
                60,
                TimeUnit.SECONDS,
                LinkedBlockingQueue()
            )
        }
        return mExecutorService!!
    }

    fun startDownload(
        url: String,
        filename: String,
        filePath: String,
        listener: DownloadListener
    ): Int {
        return startDownload(url, filename, filePath, -1, true, listener)
    }

    fun startDownload(
        url: String,
        filename: String,
        filePath: String,
        threadPool: Int,
        listener: DownloadListener
    ): Int {
        return startDownload(url, filename, filePath, threadPool, false, listener)
    }

    fun startDownload(
        url: String,
        filename: String,
        filePath: String,
        threadPool: Int,
        autoSize: Boolean,
        listener: DownloadListener
    ): Int {
        var threadSize = THREAD_SIZE
        if (threadPool != THREAD_SIZE) {
            threadSize = threadPool
        }
        val finalThreadSize = threadSize
        Log.d(
            TAG,
            "download thread size:$finalThreadSize"
        )
        val runnable = Runnable {
            try {
                val connect = Http.getHttp(url, false, 0, 0, 0)
                connect!!.connect()
                Log.d(
                    TAG,
                    "code:" + connect.responseCode
                )
                if (connect.responseCode == HttpURLConnection.HTTP_OK) {
                    val responseMessage = connect.responseMessage
                    Log.d(TAG, responseMessage)
                    val contentLength =
                        Http.getHeaderFieldLong(connect, "Content-Length")
                    var threadPoolSize = finalThreadSize
                    if (autoSize) {
                        threadPoolSize = AutoSizeThreadPool.getThreadSize(contentLength)
                    }
                    val downloadTask = DownloadTask(
                        filename,
                        filePath,
                        url,
                        threadPoolSize,
                        contentLength,
                        listener
                    )
                    downloadTask.start()
                    runningTasks.add(downloadTask)
                    try {
                        connect.disconnect()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    listener.downloadFailed(
                        url.hashCode(),
                        filename,
                        filePath,
                        "download url error code:" + connect.responseCode
                    )
                }
            } catch (e: IOException) {
                listener.downloadFailed(url.hashCode(), filename, filePath, e.toString())
            }
        }
        executorService().execute(runnable)
        return url.hashCode()
    }

    fun recyclerTask(downLoadTask: DownloadTask) {
        runningTasks.remove(downLoadTask)
    }

    companion object {
        private const val TAG = "DownloadDispatcher"

        @Volatile
        private var sDownloadDispatcher: DownloadDispatcher? = null
        private val CPU_COUNT = Runtime.getRuntime().availableProcessors()
        private val THREAD_SIZE = 3.coerceAtLeast((CPU_COUNT - 1).coerceAtMost(6))
        private val CORE_POOL_SIZE = THREAD_SIZE
        val instance: DownloadDispatcher?
            get() {
                if (sDownloadDispatcher == null) {
                    synchronized(DownloadDispatcher::class.java) {
                        if (sDownloadDispatcher == null) {
                            sDownloadDispatcher = DownloadDispatcher()
                        }
                    }
                }
                return sDownloadDispatcher
            }
    }
}