package com.speaker.torrent.datasource.def

const val KEY_REPLACE_WORD = "key_replace_word"
const val KEY_REPLACE_PAGE = "key_replace_page"
const val KEY_REPLACE_HOSE = "key_replace_host"

const val KEY_DETAIL_REPLACE_DETAIL = "key_replace_detail"

const val KEY_MAGNET_PREFIX = "magnet:?xt=urn:btih:"

const val MIRROR_SITE = "http://178.62.14.173/t_api/dm/engine/crp/basic_mirror.php?"


enum class TorrentSrc {
    ZERO_MAGNET,
    TOR_LOCK,
    X_1337,
    BT_SHOW,
    YU_HUA_GE
}