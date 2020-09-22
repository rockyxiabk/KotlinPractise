package com.rocky.core.extention

import android.content.Context
import android.content.SharedPreferences

private fun sp(ctx: Context, name: String): SharedPreferences.Editor {
    return ctx.getSharedPreferences(name, Context.MODE_PRIVATE).edit()
}

fun putInt(ctx: Context, name: String, key: String, value: Int) {
    sp(ctx, name).putInt(key, value).apply()
}

fun putBoolean(ctx: Context, name: String, key: String, value: Boolean) {
    sp(ctx, name).putBoolean(key, value).apply()
}

fun putString(ctx: Context, name: String, key: String, value: String) {
    sp(ctx, name).putString(key, value).apply()
}

fun putLong(ctx: Context, name: String, key: String, value: Long) {
    sp(ctx, name).putLong(key, value).apply()
}

fun putFloat(ctx: Context, name: String, key: String, value: Float) {
    sp(ctx, name).putFloat(key, value).apply()
}

fun clear(ctx: Context, name: String) {
    sp(ctx, name).clear().apply()
}

fun remove(ctx: Context, name: String, key: String) {
    sp(ctx, name).remove(key).apply()
}