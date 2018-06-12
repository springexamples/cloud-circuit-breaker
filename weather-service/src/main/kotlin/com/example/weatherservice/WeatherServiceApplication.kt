package com.example.weatherservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ThreadLocalRandom

@EnableDiscoveryClient
@SpringBootApplication
class WeatherServiceApplication

fun main(args: Array<String>) {
    runApplication<WeatherServiceApplication>(*args)
}

@RestController
class WeatherController {

    @Value("\${server.port}")
    lateinit var port: String;

    companion object {
        val WEATHER_VALUES = arrayOf("rainy", "cloudy", "sunny", "coldy")
    }

    @GetMapping("/weather")
    fun getWeather() =
            "${WEATHER_VALUES[ThreadLocalRandom.current().nextInt(1, WEATHER_VALUES.size)]} from $port"
}