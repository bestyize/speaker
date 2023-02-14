package com.speaker.torrent.protocol

class TorrentInfo {
    var detailUrl: String ?= null
    var magnetUrl: String ?= null
    var title: String ?= null
    var date: String ?= null
    var size: String ?= null
    var torrentItems = ArrayList<TorrentItem>()
}

class TorrentItem {
    var fileName: String ?= null
    var fileSize: String ?= null
}