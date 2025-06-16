package br.com.fiap.automotivesalesservice.acceptance.steps

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class ListSoldVehiclesAcceptanceTest {
    private val vehiclesUrl = "/vehicles"

    @Autowired private lateinit var testRestTemplate: TestRestTemplate

    private lateinit var response: ResponseEntity<String>
    private lateinit var requestInput: Map<String, Any>

    companion object {
        @Container @ServiceConnection val postgres = PostgreSQLContainer("postgres:16.3")
    }
}
