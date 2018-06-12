package com.example.weatherapp

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
class WeatherAppApplication

fun main(args: Array<String>) {
    runApplication<WeatherAppApplication>(*args)
}

@Configuration
class Config {
    @Bean
    @LoadBalanced
    fun restTemplate() = RestTemplateBuilder().build()
}

@Service
class WeatherService {

    @Autowired
    lateinit var restTemplate: RestTemplate;

    @HystrixCommand(fallbackMethod = "unknown")
    fun getWeather(): String {
        return restTemplate.getForEntity("http://weather-service/weather",
                String::class.java).body.orEmpty()
    }

    fun unknown() = "unknown"
}

@RestController
class WeatherController(val weatherService: WeatherService) {

    @GetMapping("/current/weather")
    fun getWeather(): String {
        return "current weather is: ${weatherService.getWeather()}"
    }
}
