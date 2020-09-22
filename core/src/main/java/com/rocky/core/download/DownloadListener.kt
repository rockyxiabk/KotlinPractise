package com.rocky.core.download

interface DownloadListener {
    fun downloadStart(
        downloadId: Int,
        filename: String?,
        filePath: String?
    )

    fun downloadProgress(
        downloadId: Int,
        progress: Int,
        total: Int,
        filename: String?,
        filePath: String?
    )

    fun downloadPause(
        downloadId: Int,
        progress: Int,
        total: Int)

    fun downloadSuccess(
        downloadId: Int,
        filename: String?,
        filePath: String?
    )

    fun downloadFailed(
        downloadId: Int,
        filename: String?,
        filePath: String?,
        msg: String
    )
}