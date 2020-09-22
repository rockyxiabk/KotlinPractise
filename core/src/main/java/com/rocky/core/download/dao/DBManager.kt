package com.rocky.core.download.dao

import android.content.Context
import android.util.Log

class DBManager private constructor() {
    @Synchronized
    fun initDB(context: Context) {
        mThreadInfoDao = ThreadInfoDao(context)
    }

    @Synchronized
    fun insert(threadInfo: ThreadInfo?) {
        if (threadInfo != null) {
            mThreadInfoDao!!.insert(threadInfo)
        }
    }

    @Synchronized
    fun delete(url: String) {
        Log.d("tag", "----delete by url:$url")
        mThreadInfoDao!!.delete(url)
    }

    @Synchronized
    fun update(threadInfo: ThreadInfo) {
        threadInfo.url?.let {
            mThreadInfoDao!!.update(
                it,
                threadInfo.id,
                threadInfo.progress,
                threadInfo.finished
            )
        }
    }

    fun getThreadList(url: String?): List<ThreadInfo?>? {
        return url?.let { mThreadInfoDao!!.getThreadInfos(it) }
    }

    companion object {
        private var sDataBaseManager: DBManager? = null
        private var mThreadInfoDao: ThreadInfoDao? = null
        val instance: DBManager?
            get() {
                if (sDataBaseManager == null) {
                    sDataBaseManager = DBManager()
                }
                return sDataBaseManager
            }
    }
}