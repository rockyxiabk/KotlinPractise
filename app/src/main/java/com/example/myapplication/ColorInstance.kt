package com.example.myapplication

class ColorInstance(color: Int, msg: String) {
    // 工厂函数
    companion object {
        fun fromColor(color: Int, msg: String): ColorInstance {
            return ColorInstance(color, msg)
        }
    }
}