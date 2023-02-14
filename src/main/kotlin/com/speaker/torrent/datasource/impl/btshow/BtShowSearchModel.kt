package com.speaker.torrent.datasource.impl.btshow

import com.speaker.network.get
import com.speaker.torrent.datasource.def.KEY_REPLACE_PAGE
import com.speaker.torrent.datasource.def.KEY_REPLACE_WORD
import com.speaker.torrent.datasource.def.MIRROR_SITE
import com.speaker.torrent.datasource.def.urlEncode
import com.speaker.torrent.datasource.impl.base.TorrentSearchModel
import com.speaker.torrent.protocol.TorrentInfo
import com.speaker.util.Log
import org.jsoup.Jsoup

private const val TAG = "BtShowSearchModel"

private const val BASE_SEARCH_URL = "https://btsow.beauty/search/$KEY_REPLACE_WORD/page/$KEY_REPLACE_PAGE"

object BtShowSearchModel: TorrentSearchModel {
    override fun searchParse(key: String, page: Int): List<TorrentInfo> {
        val originUrl = BASE_SEARCH_URL.replace(KEY_REPLACE_WORD, key.urlEncode()).replace(KEY_REPLACE_PAGE, page.toString())
        val content = get(getMirrorSite(originUrl))
        return parseSearch(content)
    }

    override fun getMagnet(link: String?): String {
        link ?: return ""
        try {
            val content = get(getMirrorSite(link))
            return Jsoup.parse(content).body().getElementById("magnetLink")?.text() ?: ""
        }catch (e: Exception) {
            Log.i(TAG, "getMagnet, error = $e")
        }
        return ""
    }

    override fun getMirrorSite(originUrl: String): String {
        return "${MIRROR_SITE}source=57&url=${originUrl.urlEncode()}"
    }


    private fun parseSearch(resp: String) : List<TorrentInfo> {
        val list = mutableListOf<TorrentInfo>()
        try {
            val dataList = Jsoup.parse(resp).body().getElementsByClass("data-list")[0].getElementsByClass("row")
            dataList.forEach {
                if (it.getElementsByClass("size").size == 0) return@forEach
                val size = it.getElementsByClass("size")[0].text()
                val date = it.getElementsByClass("date").text()
                val link = "https:" + it.getElementsByTag("a")[0].attr("href")
                val title = it.getElementsByClass("file").text()
                list.add(TorrentInfo().apply {
                    this.title = title
                    this.detailUrl = link
                    this.date = date
                    this.size = size
                })
            }
        } catch (e : Exception) {
            Log.i(TAG, "parseSearch, error = $e")
        }
        return list
    }
}