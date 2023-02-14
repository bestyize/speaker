package com.speaker.torrent.datasource.impl.torlock

import com.speaker.network.get
import com.speaker.torrent.datasource.def.KEY_REPLACE_PAGE
import com.speaker.torrent.datasource.def.KEY_REPLACE_WORD
import com.speaker.torrent.datasource.def.MIRROR_SITE
import com.speaker.torrent.datasource.def.urlEncode
import com.speaker.torrent.datasource.impl.base.TorrentSearchModel
import com.speaker.torrent.protocol.TorrentInfo
import com.speaker.util.Log
import org.jsoup.Jsoup

private const val BASE_SEARCH_URL = "https://www.torlock.com/all/torrents/$KEY_REPLACE_WORD/$KEY_REPLACE_PAGE.html"

private const val BASE_DETAIL_URL = "https://www.torlock.com"

private const val TAG = "TorLockSearchModel"

object TorLockSearchModel : TorrentSearchModel{

    override fun searchParse(key: String, page: Int): List<TorrentInfo> {
        val originUrl =
            BASE_SEARCH_URL.replace(KEY_REPLACE_WORD, key.urlEncode()).replace(KEY_REPLACE_PAGE, page.toString())
        return parseSearch(
            get(
                getMirrorSite(originUrl)
            )
        )
    }

    private fun parseSearch(resp: String): List<TorrentInfo> {
        val list = mutableListOf<TorrentInfo>()
        try {
            val items = Jsoup.parse(resp).body().getElementsByClass("table-responsive")[1].getElementsByTag("tr")
            items.forEach {
                val size = it.getElementsByClass("ts").text()
                if (size.isBlank()) return@forEach
                val date = it.getElementsByClass("td").text()
                val div = it.getElementsByTag("div")[0]
                val link = div.getElementsByTag("a")[0].attr("href")
                if (!link.startsWith("/torrent")) return@forEach
                val title = div.text()
                list.add(TorrentInfo().apply {
                    this.title = title
                    this.detailUrl = BASE_DETAIL_URL + link
                    this.size = size
                    this.date = date
                })
            }
        } catch (e: Exception) {
            Log.i(TAG, "parseSearch, error = $e")
        }
        return list
    }

    override fun getMagnet(link: String?): String {
        link ?: return ""
        val content = get(getMirrorSite(link))
        try {
            val body = Jsoup.parse(content).body()
            return body.getElementsByClass("fa-magnet").parents()[0].attr("href")
        } catch (e: Exception) {
            Log.i(TAG, "getMagnet, error = $e")
        }

        return ""

    }

    override fun getMirrorSite(originUrl: String): String {
        return "${MIRROR_SITE}source=51&url=${originUrl.urlEncode()}"
    }


}