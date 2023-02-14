package com.speaker.util

import java.text.SimpleDateFormat

private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss")

object Log {
    fun i(tag: String, msg: String) {
        println("${sdf.format(System.currentTimeMillis())}\t$tag\t$msg")
    }
}