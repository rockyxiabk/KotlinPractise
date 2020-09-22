package com.rocky.core.download.dao

class ThreadInfo {
    var id = 0
    var tag: String? = null
    var url: String? = null
    var start: Long = 0
    var end: Long = 0
    var progress: Long = 0
    var finished: Long = 0

    constructor()
    constructor(
        id: Int,
        tag: String?,
        url: String?,
        start: Long,
        end: Long,
        finished: Long
    ) {
        this.id = id
        this.tag = tag
        this.url = url
        this.start = start
        this.end = end
        this.finished = finished
    }

    override fun toString(): String {
        return "ThreadInfo{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", uri='" + url + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", progress=" + progress +
                ", finished=" + finished +
                '}'
    }
}