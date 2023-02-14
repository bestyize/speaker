package com.speaker.torrent.datasource

import com.speaker.torrent.datasource.def.TorrentSrc
import com.speaker.torrent.util.JsonUtils
import org.junit.jupiter.api.Test

class DataSourceFactoryTest {

    @Test
    fun testReq() {
//        JsonUtils.printToJson(DataSourceFactory.newService().search("流浪地球"))
//        JsonUtils.printToJson(DataSourceFactory.newService(TorrentSrc.X_1337.ordinal).search("game of thrones"))
//        JsonUtils.printToJson(DataSourceFactory.newService(TorrentSrc.TOR_LOCK.ordinal).search("流浪地球", 1))
//        JsonUtils.printToJson(DataSourceFactory.newService(TorrentSrc.BT_SHOW.ordinal).search("流浪地球", 1))
        JsonUtils.printToJson(DataSourceFactory.newService(TorrentSrc.YU_HUA_GE.ordinal).search("流浪地球", 1))
    }

    @Test
    fun testMagnet() {
//        println(DataSourceFactory.newService(TorrentSrc.X_1337.ordinal).findMagnetUrl("https://www.1337xx.to/torrent/3769746/Game-of-Thrones-S08E06-720p-WEB-H264-MEMENTO-eztv/"))
//        println(
//            DataSourceFactory.newService(TorrentSrc.TOR_LOCK.ordinal)
//                .findMagnetUrl("https://www.torlock.com/torrent/65022210/29-%E7%81%AB%E7%88%86%E6%8E%A8%E8%8D%90%E6%9E%81%E5%93%81%E5%95%AA%E5%95%AA%E5%8D%8A%E7%B3%96%E5%A5%B3%E7%A5%9E%E6%8B%89%E9%82%BB%E5%B1%85%E5%A5%B3%E5%84%BF%E4%B8%8B%E6%B5%B705-09-%E9%9D%93%E4%B9%B3%E7%BE%8E%E8%85%BF%E9%A6%92%E5%A4%B4%E4%B8%80%E7%BA%BF%E5%A4%A9-%E6%9A%B4%E6%8F%92.html")
//        )
//        println(DataSourceFactory.newService(TorrentSrc.BT_SHOW.ordinal).findMagnetUrl("https://btsow.beauty/magnet/detail/hash/12A6C877D4D76903B7A66D758B50077A39415EEA"))
        println(DataSourceFactory.newService(TorrentSrc.YU_HUA_GE.ordinal).findMagnetUrl("https://www.yhg345.buzz/hash/6642882.html"))
    }
}