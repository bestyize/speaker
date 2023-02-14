package com.speaker.torrent.datasource.impl.zeromagnet

import com.speaker.torrent.util.JsonUtils

import org.junit.jupiter.api.Test

class ZeroMagnetSearchModelTest {
    @Test
    fun testSearch() {
        val list = ZeroMagnetSearchModel.searchParse("复仇者联盟", 1)
        list.forEach {
            it.magnetUrl = ZeroMagnetSearchModel.getMagnet(it.detailUrl)
        }
        println(JsonUtils.toJson(list))
    }
}