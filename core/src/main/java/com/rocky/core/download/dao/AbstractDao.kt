package com.rocky.core.download.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.io.File

abstract class AbstractDao<T>(context: Context) {
    private val mHelper: DBHelper
    protected val writableDatabase: SQLiteDatabase
        get() = mHelper.writableDatabase

    protected val readableDatabase: SQLiteDatabase
        get() = mHelper.readableDatabase

    fun close() {
        mHelper.close()
    }

    init {
        val filePath: String = try {
            context.getExternalFilesDir(null)?.absolutePath + File.separator + "database/download.db"
        } catch (e: Exception) {
            "download.db"
        }
        mHelper = DBHelper(context, filePath)
    }
}