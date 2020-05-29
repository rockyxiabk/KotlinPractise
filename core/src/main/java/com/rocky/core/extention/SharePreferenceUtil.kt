package com.rocky.core.extention

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("CommitPrefEdits")
fun putIntSharePreferences(ctx: Context, name: String, value: Int) {
    ctx.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putInt(name, value)
}