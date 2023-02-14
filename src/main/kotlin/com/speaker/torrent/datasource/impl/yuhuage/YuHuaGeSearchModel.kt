package com.speaker.torrent.datasource.impl.yuhuage

import com.speaker.network.get
import com.speaker.torrent.datasource.def.KEY_REPLACE_PAGE
import com.speaker.torrent.datasource.def.KEY_REPLACE_WORD
import com.speaker.torrent.datasource.def.urlEncode
import com.speaker.torrent.datasource.impl.base.TorrentSearchModel
import com.speaker.torrent.protocol.TorrentInfo
import com.speaker.util.Log
import org.jsoup.Jsoup

private const val TAG = "YuHuaGeSearchModel"

private const val BASE_URL = "https://www.yhg345.buzz"

private const val BASE_SEARCH_URL = "$BASE_URL/search/$KEY_REPLACE_WORD-$KEY_REPLACE_PAGE.html"

object YuHuaGeSearchModel : TorrentSearchModel {

    override fun searchParse(key: String, page: Int): List<TorrentInfo> {
        val originUrl =
            BASE_SEARCH_URL.replace(KEY_REPLACE_WORD, key.urlEncode()).replace(KEY_REPLACE_PAGE, page.toString())
        val paramsMap = mutableMapOf(
            "Referrer Policy" to "strict-origin-when-cross-origin",
            "user-agent" to "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36"
        )
        return parseSearch(get(originUrl, paramsMap))
    }

    override fun getMagnet(link: String?): String {
        link ?: return ""
        try {
            val paramsMap = mutableMapOf(
                "Referrer Policy" to "strict-origin-when-cross-origin",
                "user-agent" to "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36"
            )
            return Jsoup.parse(get(link, paramsMap)).body().getElementsByClass("download")[0].attr("href")
        } catch (e: Exception) {
            Log.i(TAG, "getMagnet, error = $e")
        }
        return ""
    }


    private fun parseSearch(resp: String): List<TorrentInfo> {
        val list = mutableListOf<TorrentInfo>()
        try {
            val items = Jsoup.parse(resp).body().getElementsByClass("search-item")
            items.forEach {
                val e1 = it.getElementsByClass("item-title")[0].getElementsByTag("a")[0]
                val title = e1.text()
                val link = BASE_URL + e1.attr("href")
                val e2 = it.getElementsByClass("item-bar")[0].getElementsByTag("span")
                val date = e2[0].text()
                val size = e2[1].text()
                list.add(TorrentInfo().apply {
                    this.title = title
                    this.detailUrl = link
                    this.date = date
                    this.size = size
                })
            }
        } catch (e: Exception) {
            Log.i(TAG, "parseSearch, error = $e")
        }
        return list
    }
}