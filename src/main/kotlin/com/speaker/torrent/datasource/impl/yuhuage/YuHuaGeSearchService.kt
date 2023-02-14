package com.speaker.torrent.datasource.impl.yuhuage

import com.speaker.torrent.datasource.impl.base.TorrentSearchService
import com.speaker.torrent.protocol.TorrentInfo

class YuHuaGeSearchService : TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        return YuHuaGeSearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return YuHuaGeSearchModel.getMagnet(url)
    }
}