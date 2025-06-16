package br.com.fiap.automotivesalesservice.acceptance.steps

import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberContextConfiguration
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@Testcontainers
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class SaveVehicleAcceptanceTest {
    private val vehiclesUrl = "/vehicles"

    @Autowired private lateinit var testRestTemplate: TestRestTemplate

    private lateinit var response: ResponseEntity<String>
    private lateinit var requestInput: Map<String, Any>

    companion object {
        @Container @ServiceConnection val postgres = PostgreSQLContainer("postgres:16.3")
    }

    @Before
    fun `should connection be established`() {
        postgres.isCreated shouldBe true
        postgres.isRunning shouldBe true
    }

    @Given("the partner has a valid vehicle available for saving")
    fun `the partner has a valid vehicle available for saving`() {
        requestInput =
            mapOf(
                "vehicleId" to UUID.randomUUID().toString(),
                "plate" to "HJK-1234",
                "price" to 10000,
                "priceCurrency" to "BRL",
                "make" to "Ford",
                "model" to "Fiesta",
                "version" to "SE",
                "yearFabrication" to "2020",
                "yearModel" to "2021",
                "kilometers" to 5000,
                "color" to "Blue",
                "status" to "AVAILABLE",
                "createdAt" to "2023-10-01T12:00:00Z",
            )
    }

    @When("the partner submits the vehicle save form")
    fun `the partner submits the vehicle save form`() {
        response = testRestTemplate.postForEntity(vehiclesUrl, requestInput, String::class.java)
    }

    @Then("the vehicle should be saved successfully")
    fun `the vehicle should be saved successfully`() {
        response.statusCode shouldBe HttpStatus.CREATED
    }

    @Given("the partner has an invalid vehicle save form missing required fields")
    fun `the partner has an invalid vehicle save form missing required fields`() {
        requestInput =
            mapOf(
                "vehicleId" to UUID.randomUUID().toString(),
                "plate" to "HJK-1234",
                "price" to 10000,
                // Missing priceCurrency, make, model, etc.
            )
    }

    @Then("the system should reject the save with a bad request error")
    fun `the system should reject the save with a bad request error`() {
        response.statusCode shouldBe HttpStatus.BAD_REQUEST
    }
}
