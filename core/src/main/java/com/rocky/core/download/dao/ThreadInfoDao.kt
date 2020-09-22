package com.rocky.core.download.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.util.*

class ThreadInfoDao(context: Context) :
    AbstractDao<ThreadInfo?>(context) {
    fun insert(info: ThreadInfo) {
        val db = writableDatabase
        db.execSQL(
            "insert into $TABLE_NAME(id, tag, url, start, end,progress, finished) values(?, ?, ?, ?, ?,?, ?)",
            arrayOf<Any?>(
                info.id,
                info.tag,
                info.url,
                info.start,
                info.end,
                info.progress,
                info.finished
            )
        )
    }

    fun delete(url: String) {
        val db = writableDatabase
        db.execSQL(
            "delete from $TABLE_NAME where url = ?",
            arrayOf<Any>(url)
        )
    }

    fun delete(threadId: Int, url: String) {
        val db = writableDatabase
        db.execSQL(
            "delete from $TABLE_NAME where url = ? and id = ? ",
            arrayOf<Any>(url, threadId)
        )
    }

    fun update(
        url: String,
        threadId: Int,
        progress: Long,
        finished: Long
    ) {
        val db = writableDatabase
        db.execSQL(
            "update $TABLE_NAME set finished = ? , progress = ? where url = ? and id = ? ",
            arrayOf<Any>(finished, progress, url, threadId)
        )
    }

    fun getThreadInfos(url: String): List<ThreadInfo> {
        val list: MutableList<ThreadInfo> =
            ArrayList()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "select * from $TABLE_NAME where url = ?",
            arrayOf(url)
        )
        while (cursor.moveToNext()) {
            val info =
                ThreadInfo()
            info.id = cursor.getInt(cursor.getColumnIndex("id"))
            info.tag = cursor.getString(cursor.getColumnIndex("tag"))
            info.url = cursor.getString(cursor.getColumnIndex("url"))
            info.end = cursor.getLong(cursor.getColumnIndex("end"))
            info.start = cursor.getLong(cursor.getColumnIndex("start"))
            info.progress = cursor.getLong(cursor.getColumnIndex("progress"))
            info.finished = cursor.getLong(cursor.getColumnIndex("finished"))
            list.add(info)
        }
        cursor.close()
        return list
    }

    companion object {
        private const val TABLE_NAME = "threadinfo"
        fun createTable(db: SQLiteDatabase) {
            db.execSQL("create table $TABLE_NAME(_id integer primary key autoincrement, id integer, tag text, url text, start long, end long,progress long, finished long)")
        }

        fun dropTable(db: SQLiteDatabase) {
            db.execSQL("drop table if exists $TABLE_NAME")
        }
    }
}