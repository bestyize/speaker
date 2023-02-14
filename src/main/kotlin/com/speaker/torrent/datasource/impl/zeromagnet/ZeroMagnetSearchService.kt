package com.speaker.torrent.datasource.impl.zeromagnet

import com.speaker.torrent.datasource.impl.base.TorrentSearchService
import com.speaker.torrent.protocol.TorrentInfo
import com.speaker.util.Log

private const val TAG = "ZeroMagnetSearchService"

class ZeroMagnetSearchService : TorrentSearchService {
    override fun search(key: String, page: Int): List<TorrentInfo> {
        Log.i(TAG, "search, key = $key, page = $page")
        return ZeroMagnetSearchModel.searchParse(key, page)
    }

    override fun findMagnetUrl(url: String): String {
        return ZeroMagnetSearchModel.getMagnet(url)
    }
}