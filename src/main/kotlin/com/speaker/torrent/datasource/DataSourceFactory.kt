package com.speaker.torrent.datasource

import com.speaker.torrent.datasource.def.TorrentSrc
import com.speaker.torrent.datasource.impl.base.TorrentSearchService
import com.speaker.torrent.datasource.impl.btshow.BtShowSearchModel
import com.speaker.torrent.datasource.impl.btshow.BtShowSearchService
import com.speaker.torrent.datasource.impl.torlock.TorLockSearchService
import com.speaker.torrent.datasource.impl.x1337.X1337SearchService
import com.speaker.torrent.datasource.impl.yuhuage.YuHuaGeSearchService
import com.speaker.torrent.datasource.impl.zeromagnet.ZeroMagnetSearchService

object DataSourceFactory {

    private val servicesPool by lazy{ mutableMapOf<Int, TorrentSearchService>().apply {
        put(TorrentSrc.ZERO_MAGNET.ordinal, ZeroMagnetSearchService())
        put(TorrentSrc.X_1337.ordinal, X1337SearchService())
        put(TorrentSrc.TOR_LOCK.ordinal, TorLockSearchService())
        put(TorrentSrc.BT_SHOW.ordinal, BtShowSearchService())
        put(TorrentSrc.YU_HUA_GE.ordinal, YuHuaGeSearchService())
    }}

    fun newService(torrentSrc: Int = TorrentSrc.ZERO_MAGNET.ordinal) : TorrentSearchService {
        return servicesPool[torrentSrc] ?: ZeroMagnetSearchService()
    }
}