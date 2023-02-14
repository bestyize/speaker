package com.speaker.discuss

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class SpeakerApplication

fun main(args: Array<String>) {
	runApplication<SpeakerApplication>(*args)
}
