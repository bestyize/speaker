package com.speaker.discuss.music.controller

import com.speaker.discuss.music.base.protocol.BaseResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MusicController {
    @RequestMapping("/jb/api/license/get")
    fun getJetbrainLicenseServer(): ResponseEntity<BaseResponse> {
        val httpHeaders = HttpHeaders().apply {
            add("Content-Type", "application/json")
        }
        val resp = BaseResponse().apply {
            code = 0
            data = "{\"download_link\" : \"https://www.baidu.com\"}"
        }
        return ResponseEntity(resp, httpHeaders, HttpStatus.OK)
    }
}