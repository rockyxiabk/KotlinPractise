package com.rocky.core.download

import com.rocky.core.download.dao.DBManager
import com.rocky.core.download.dao.ThreadInfo
import java.io.File
import java.io.InputStream
import java.io.RandomAccessFile
import java.net.HttpURLConnection

class DownloadThread(
    //文件下载的url
    private val url: String,
    //文件的名称
    private val name: String,
    //文件的路径
    private val filePath: String,
    //线程id
    private val threadId: Int,
    //每个线程下载开始的位置
    private val start: Long,
    //每个线程下载结束的位置
    private val end: Long,
    //每个线程的下载进度
    private var progress: Long,
    //文件的总大小 content-length
    private val contentLength: Long,
    threadInfo: ThreadInfo?,
    downloadListener: DownloadListener
) : Runnable {
    //线程的状态
    private var mStatus = STATUS_DOWNLOADING

    //当前线程是否下载完成
    private var isFinished = false

    private val downloadListener: DownloadListener
    private val threadInfo: ThreadInfo?
    override fun run() {
        var inputStream: InputStream? = null
        var randomAccessFile: RandomAccessFile? = null
        try {
            saveToDb()
            val connect = Http.getHttp(url, true, start, end, contentLength)
            if (connect!!.responseCode == HttpURLConnection.HTTP_OK || connect.responseCode == HttpURLConnection.HTTP_PARTIAL) {
                downloadListener.downloadStart(url.hashCode(), name, filePath)
                inputStream = connect.inputStream
                //保存文件的路径
                val file = File(filePath + name)
                randomAccessFile = RandomAccessFile(file, "rwd")
                //seek从哪里开始
                randomAccessFile.seek(start)
                var length: Int
                val bytes = ByteArray(4 * 1024)
                while (inputStream.read(bytes).also { length = it } != -1) {
                    if (mStatus == STATUS_STOP) {
                        downloadListener.downloadPause(
                            url.hashCode(),
                            length,
                            contentLength.toInt()
                        )
                        break
                    }
                    //写入
                    randomAccessFile.write(bytes, 0, length)
                    progress += length
                    //实时去更新下进度条
                    downloadListener.downloadProgress(
                        url.hashCode(),
                        length,
                        contentLength.toInt(),
                        name,
                        filePath
                    )
                    saveToDb()
                }
                if (mStatus != STATUS_STOP) {
                    isFinished = true
                    saveToDb()
                    Thread.sleep(300)
                    downloadListener.downloadSuccess(url.hashCode(), name, filePath)
                }
            }
            try {
                connect.disconnect()
            } catch (e: Exception) {
                downloadListener.downloadFailed(url.hashCode(), name, filePath, e.toString())
                e.printStackTrace()
            }
        } catch (e: Exception) {
            downloadListener.downloadFailed(url.hashCode(), name, filePath, e.toString())
        } finally {
            Http.close(inputStream)
            Http.close(randomAccessFile)
        }
    }

    fun stop() {
        mStatus = STATUS_STOP
    }

    private fun saveToDb() {
        try {
            threadInfo.let {
                it?.id=threadId
                it?.tag=name
                it?.url=url
                it?.start=start
                it?.end=end
                it?.progress=progress
                it?.finished=if (isFinished) 1 else 0
            }
            DBManager.instance?.update(threadInfo!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val STATUS_DOWNLOADING = 1
        private const val STATUS_STOP = 2
    }

    init {
        this.threadInfo = threadInfo
        this.downloadListener = downloadListener
    }
}