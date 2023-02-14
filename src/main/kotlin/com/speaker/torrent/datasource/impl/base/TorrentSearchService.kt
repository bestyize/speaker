package com.speaker.torrent.datasource.impl.base

import com.speaker.torrent.protocol.TorrentInfo

interface TorrentSearchService {

    fun search(key: String, page: Int = 1) : List<TorrentInfo>

    fun findMagnetUrl(url: String) : String
}