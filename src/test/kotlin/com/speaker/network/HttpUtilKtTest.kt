package com.speaker.network

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class HttpUtilKtTest {

    @Test
    fun get() {
        System.setProperty("https.proxyHost", "127.0.0.1")
        System.setProperty("https.proxyPort", "1095")
        println(get("https://www.torrentkitty.se/search/%E7%9C%9F%E5%AE%9E%E5%BC%BA%E5%A5%B8/2"))
    }
}