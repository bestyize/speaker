package com.speaker.torrent.datasource.impl.x1337

import com.speaker.network.get
import com.speaker.torrent.datasource.def.KEY_REPLACE_PAGE
import com.speaker.torrent.datasource.def.KEY_REPLACE_WORD
import com.speaker.torrent.datasource.def.MIRROR_SITE
import com.speaker.torrent.datasource.def.urlEncode
import com.speaker.torrent.datasource.impl.base.TorrentSearchModel
import com.speaker.torrent.protocol.TorrentInfo
import com.speaker.util.Log
import org.jsoup.Jsoup

private const val TAG = "X1337SearchModel"

private const val BASE_SEARCH_URL = "https://www.1337xx.to/search/$KEY_REPLACE_WORD/$KEY_REPLACE_PAGE/"
private const val BASE_DETAIL_URL = "https://www.1337xx.to"

object X1337SearchModel : TorrentSearchModel{

    override fun searchParse(key: String, page: Int): List<TorrentInfo> {
        val reqUrl =
            BASE_SEARCH_URL.replace(KEY_REPLACE_WORD, key.urlEncode()).replace(KEY_REPLACE_PAGE, page.toString())
        val resp = get(getMirrorSite(reqUrl))
        return parseSearch(resp)
    }

    private fun parseSearch(resp: String): List<TorrentInfo> {
        val list = mutableListOf<TorrentInfo>()
        val body = Jsoup.parse(resp).body()
        try {
            val items = body.getElementsByTag("tbody")[0].getElementsByTag("tr")
            items.forEach {
                val item1 = it.getElementsByClass("coll-1 name")
                val fileName = item1.text()
                val link = BASE_DETAIL_URL + item1[0].getElementsByTag("a")[1].attr("href")
                val size = it.getElementsByClass("coll-4 size mob-uploader").text()
                list.add(TorrentInfo().apply {
                    this.title = fileName
                    this.detailUrl = link
                    this.size = size
                })

            }
            return list
        } catch (e: Exception) {
            Log.i(TAG, e.toString())
        }
        return listOf()

    }

    override fun getMagnet(link: String?): String {
        link ?: return ""
        val body = Jsoup.parse(get(getMirrorSite(link)))
        try {
            return body.getElementsByClass(MAGNET_CLASS_NAME)[0].attr(
                "href"
            )
        } catch (_: Exception) {
            Log.i(TAG, "get magnet failed, url = $link")
        }
        return ""

    }

    override fun getMirrorSite(originUrl: String): String {
        return "${MIRROR_SITE}source=4&url=${originUrl.urlEncode()}"
    }


    private const val MAGNET_CLASS_NAME = "torrentdown2"

}