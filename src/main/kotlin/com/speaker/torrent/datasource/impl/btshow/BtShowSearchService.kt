package com.speaker.torrent.datasource.impl.btshow

import com.speaker.torrent.datasource.impl.base.TorrentSearchService
import com.speaker.torrent.protocol.TorrentInfo

class BtShowSearchService : TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        return BtShowSearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return BtShowSearchModel.getMagnet(url)
    }
}