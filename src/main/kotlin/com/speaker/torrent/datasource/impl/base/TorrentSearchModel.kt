package com.speaker.torrent.datasource.impl.base

import com.speaker.torrent.protocol.TorrentInfo

interface TorrentSearchModel {
    fun searchParse(key: String, page: Int) : List<TorrentInfo>

    fun getMagnet(link: String?): String

    fun getMirrorSite(originUrl: String): String {return ""}
}