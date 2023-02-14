package com.speaker.torrent.datasource.impl.torlock

import com.speaker.torrent.datasource.impl.base.TorrentSearchService
import com.speaker.torrent.protocol.TorrentInfo

class TorLockSearchService : TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        return TorLockSearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return TorLockSearchModel.getMagnet(url)
    }
}