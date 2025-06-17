package br.com.fiap.automotivesalesservice.acceptance.steps

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class UpdateVehicleAcceptanceTest {
    private val vehiclesUrl = "/vehicles"

    @Autowired private lateinit var testRestTemplate: TestRestTemplate

    private lateinit var response: ResponseEntity<String>
    private lateinit var requestInput: MutableMap<String, Any>

    companion object {
        @Container @ServiceConnection val postgres = PostgreSQLContainer("postgres:16.3")
    }

    @Given("the partner has a valid vehicle for updating")
    fun `the partner has a valid vehicle for updating`() {
        requestInput =
            mutableMapOf(
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
                "updatedAt" to "2023-11-01T12:00:00Z",
            )
    }

    @When("the partner submits the vehicle update form")
    fun `the partner submits the vehicle update form`() {
        response =
            testRestTemplate.exchange(
                "$vehiclesUrl/${requestInput["vehicleId"]}",
                HttpMethod.PUT,
                HttpEntity(requestInput),
                String::class.java,
            )
    }

    @Then("the vehicle should be updated successfully")
    fun `the vehicle should be updated successfully`() {
        response.statusCode shouldBe HttpStatus.OK
    }

    @Given("the partner has an invalid vehicle update form missing required fields")
    fun `the partner has an invalid vehicle update form missing required fields`() {
        requestInput =
            mutableMapOf(
                "vehicleId" to UUID.randomUUID().toString(),
                "plate" to "HJK-1234",
                // Missing price
                "priceCurrency" to "BRL",
                "make" to "Ford",
                "model" to "Fiesta",
                "version" to "SE",
                "yearFabrication" to "2020",
                "yearModel" to "2021",
                "kilometers" to 5000,
                "color" to "Blue",
                // Missing status
            )
    }

    @Then("the system should reject the update with a bad request error")
    fun `the system should reject the update with a bad request error`() {
        response.statusCode shouldBe HttpStatus.BAD_REQUEST
    }

    @Given("the partner attempts to update a non-existent vehicle")
    fun `the partner attempts to update a non-existent vehicle`() {
        requestInput.put("vehicleId", UUID.randomUUID().toString())
        response =
            testRestTemplate.exchange(
                "$vehiclesUrl/${requestInput["vehicleId"]}",
                HttpMethod.PUT,
                HttpEntity(requestInput),
                String::class.java,
            )
    }

    @Then("the system should reject the update with a not found error")
    fun `the system should reject the update with a not found error`() {
        response.statusCode shouldBe HttpStatus.NOT_FOUND
    }
}
