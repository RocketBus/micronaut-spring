package com.example

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Service
class ExampleService {
    private val logger = LoggerFactory.getLogger(ExampleService::class.java)

    fun run(value: String) {
        logger.info("Received value: $value")
    }
}

@RestController
@RequestMapping("/api/logs")
class ExampleController(private val exampleService: ExampleService) {

    @PostMapping
    fun logValue(@RequestBody exampleRequest: ExampleRequest): ResponseEntity<String> {
        exampleService.run(exampleRequest.value)
        return ResponseEntity.ok("Value logged successfully")
    }
}

data class ExampleRequest(val value: String)