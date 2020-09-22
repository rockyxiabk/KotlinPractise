package com.rocky.core.download

object AutoSizeThreadPool {
    // 1 connection: [0, 1MB)
    private const val ONE_CONNECTION_UPPER_LIMIT = 1024 * 1024 // 1MB
        .toLong()

    // 2 connection: [1MB, 5MB)
    private const val TWO_CONNECTION_UPPER_LIMIT = 5 * 1024 * 1024 // 5MB
        .toLong()

    // 3 connection: [5MB, 50MB)
    private const val THREE_CONNECTION_UPPER_LIMIT = 50 * 1024 * 1024 // 50MB
        .toLong()

    // 4 connection: [50MB, 100MB)
    private const val FOUR_CONNECTION_UPPER_LIMIT = 100 * 1024 * 1024 // 100MB
        .toLong()

    fun getThreadSize(totalLength: Long): Int {
        if (totalLength < ONE_CONNECTION_UPPER_LIMIT) {
            return 1
        }
        if (totalLength < TWO_CONNECTION_UPPER_LIMIT) {
            return 2
        }
        if (totalLength < THREE_CONNECTION_UPPER_LIMIT) {
            return 3
        }
        return if (totalLength < FOUR_CONNECTION_UPPER_LIMIT) {
            4
        } else 5
    }
}