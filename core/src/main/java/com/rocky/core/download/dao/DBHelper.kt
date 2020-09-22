package com.rocky.core.download.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper internal constructor(context: Context?, path_name: String?) :
    SQLiteOpenHelper(context, path_name, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        createTable(db)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        dropTable(db)
        createTable(db)
    }

    private fun createTable(db: SQLiteDatabase) {
        ThreadInfoDao.createTable(db)
    }

    private fun dropTable(db: SQLiteDatabase) {
        ThreadInfoDao.dropTable(db)
    }

    companion object {
        private const val DB_VERSION = 2
    }
}