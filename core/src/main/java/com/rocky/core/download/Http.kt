package com.rocky.core.download

import android.util.Log
import java.io.Closeable
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object Http {
    fun getHttp(
        pathUrl: String?,
        isMulti: Boolean,
        start: Long,
        end: Long,
        contentLength: Long
    ): HttpURLConnection? {
        return try {
            val url = URL(pathUrl)
            val connection =
                url.openConnection() as HttpURLConnection
            //设置自动处理重定向问题
            connection.instanceFollowRedirects = true
            connection.connectTimeout = 20000
            connection.readTimeout = 30000
            //单文件使用多线程分块下载
            if (isMulti) {
                Log.d("Http", "multi download-start:$start end:$end")
                if (end < contentLength) {
                    connection.setRequestProperty("Range", "bytes=$start-$end")
                } else {
                    connection.setRequestProperty("Range", "bytes=$start-")
                }
            }
            connection.connect()
            connection
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun getHeaderFieldLong(
        httpURLConnection: HttpURLConnection?,
        name: String?
    ): Long {
        val field = httpURLConnection!!.getHeaderField(name)
        return try {
            field?.toLong() ?: -1L
        } catch (e: NumberFormatException) {
            -1L
        }
    }

    fun close(closeable: Closeable?) {
        try {
            closeable?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}