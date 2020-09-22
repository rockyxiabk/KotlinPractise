package com.rocky.core.download

import android.util.Log
import com.rocky.core.download.dao.DBManager
import com.rocky.core.download.dao.ThreadInfo
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class DownloadTask(
    //文件的名称
    private val filename: String,
    //文件路径
    private val filePath: String,
    //文件下载的url
    private val url: String,
    //下载文件的线程的个数
    private val mThreadSize: Int,
    //文件的大小
    private val contentLength: Long,
    listener: DownloadListener
) {

    //线程下载成功的个数，AtomicInteger
    private val mSuccessNumber =
        AtomicInteger()

    //总进度=每个线程的进度的和
    private var mTotalProgress: Long = 0

    //正在执行下载任务的runnable
    private val downloadThreads: MutableList<DownloadThread>
    private val downloadListener: DownloadListener
    fun start() {
        //每个线程的下载的大小threadSize
        val threadSize = contentLength / mThreadSize
        val threadList: List<ThreadInfo?>? = DBManager.instance?.getThreadList(url)
        for (threadId in 0 until mThreadSize) {
            //初始化的时候，需要读取数据库
            //开始下载的位置
            var start = threadId * threadSize
            //结束下载的位置
            var end = start + threadSize
            //最后一个线程块的大小以contentLength结尾
            if (threadId == mThreadSize - 1) {
                end = contentLength
            }
            var threadInfo: ThreadInfo? = getEntity(threadId, threadList)
            if (threadInfo == null) {
                threadInfo = ThreadInfo(threadId, filename, url, start, end, 0)
                DBManager.instance?.insert(threadInfo)
            } else {
                Log.d(TAG, "init: 上次保存的进度progress=$threadInfo")
                start += threadInfo.progress
            }
            if (start >= end) {
                mSuccessNumber.incrementAndGet()
                Log.d(
                    TAG,
                    "thread id:$threadId finished"
                )
                if (mSuccessNumber.get() == mThreadSize) {
                    downloadListener.downloadSuccess(url.hashCode(), filename, filePath)
                    DownloadDispatcher.instance?.recyclerTask(this@DownloadTask)
                    //如果下载完毕，清除数据库
                    DBManager.instance?.delete(url)
                    return
                } else {
                    continue
                }
            }
            val downloadThread = DownloadThread(
                url,
                filename,
                filePath,
                threadId,
                start,
                end,
                threadInfo.progress,
                contentLength,
                threadInfo,
                object : DownloadListener {
                    override fun downloadStart(
                        downloadId: Int,
                        filename: String?,
                        filePath: String?
                    ) {
                    }

                    override fun downloadProgress(
                        downloadId: Int,
                        progress: Int,
                        total: Int,
                        filename: String?,
                        filePath: String?
                    ) {
                        //叠加下progress，实时去更新进度条,这里需要synchronized
                        synchronized(this@DownloadTask) {
                            mTotalProgress += progress
                            downloadListener.downloadProgress(
                                url.hashCode(),
                                mTotalProgress.toInt(),
                                total,
                                filename,
                                filePath
                            )
                        }
                    }

                    override fun downloadPause(
                        downloadId: Int,
                        progress: Int,
                        total: Int
                    ) {
                        downloadListener.downloadPause(url.hashCode(), progress, total)
                    }

                    override fun downloadSuccess(
                        downloadId: Int,
                        filename: String?,
                        filePath: String?
                    ) {
                        mSuccessNumber.incrementAndGet()
                        if (mSuccessNumber.get() == mThreadSize) {
                            downloadListener.downloadSuccess(url.hashCode(), filename, filePath)
                            DownloadDispatcher.instance?.recyclerTask(this@DownloadTask)
                            //如果下载完毕，清除数据库
                            DBManager.instance?.delete(url)
                        }
                    }

                    override fun downloadFailed(
                        downloadId: Int,
                        filename: String?,
                        filePath: String?,
                        msg: String
                    ) {
                        //有一个线程发生异常，下载失败，需要把其它线程停止掉
                        Log.e(TAG, "downloadFailed: $msg")
                        downloadListener.downloadFailed(url.hashCode(), filename, filePath, msg)
                        stopDownload()
                    }
                })
            DownloadDispatcher.instance?.executorService()?.execute(downloadThread)
            downloadThreads.add(downloadThread)
        }
    }

    private fun getEntity(
        threadId: Int,
        entities: List<ThreadInfo?>?
    ): ThreadInfo? {
        for (entity in entities!!) {
            if (threadId == entity?.id) {
                return entity
            }
        }
        return null
    }

    private fun stopDownload() {
        for (runnable in downloadThreads) {
            runnable.stop()
        }
    }

    companion object {
        private const val TAG = "DownloadTask"
    }

    init {
        downloadThreads = ArrayList()
        downloadListener = listener
    }
}