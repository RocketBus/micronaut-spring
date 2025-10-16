package com.example

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.serde.annotation.Serdeable
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class ExampleService {
    private val logger = LoggerFactory.getLogger(ExampleService::class.java)

    fun run(value: String) {
        logger.info("Received value: $value")
    }
}

@Controller("/api/logs")
class ExampleController(private val exampleService: ExampleService) {

    @Post
    fun logValue(@Body exampleRequest: ExampleRequest): HttpResponse<String> {
        exampleService.run(exampleRequest.value)
        return HttpResponse.ok("Value logged successfully")
    }
}

@Serdeable
data class ExampleRequest(val value: String)