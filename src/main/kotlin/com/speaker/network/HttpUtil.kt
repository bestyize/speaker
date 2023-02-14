package com.speaker.network

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private const val TAG = "HttpUtil"


fun get(link: String, paramsMap: Map<String, String> = mutableMapOf(),  timeout: Int = 5000): String {
    val sb = StringBuilder()
    try {
        val url = URL(link)
        val conn = (url.openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = timeout
            readTimeout = timeout
            paramsMap.forEach { (t, u) ->  setRequestProperty(t, u)}
        }
        val reader = BufferedReader(InputStreamReader(conn.inputStream))
        var line: String?
        do {
            line = reader.readLine()
            sb.append(line)
        } while (line != null)
        reader.close()
        conn.disconnect()
        return sb.toString()
    } catch (e: Exception) {
        println("$TAG\terror : ${e.message}")
    }
    return sb.toString()

}

fun fileToString(path: String) : String {
    val sb = StringBuilder()
    val file = File(path)
    if (!file.exists()) {
        return ""
    }
    val reader = BufferedReader(InputStreamReader(FileInputStream(file)))
    var line: String?
    do {
        line = reader.readLine()
        sb.append(line)
    } while (line != null)
    reader.close()
    return sb.toString()
}
