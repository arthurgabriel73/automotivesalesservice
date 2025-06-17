package br.com.fiap.automotivesalesservice.acceptance.steps

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class OrderVehicleAcceptanceTest {

    private val ordersUrl = "/orders"

    @Autowired private lateinit var testRestTemplate: TestRestTemplate

    private lateinit var response: ResponseEntity<String>
    private lateinit var requestInput: Map<String, Any>
    private lateinit var vehicleId: String

    companion object {
        @Container @ServiceConnection val postgres = PostgreSQLContainer("postgres:16.3")
    }

    @Given("the system has a valid vehicle available for order")
    fun `the system has a valid vehicle available for order`() {
        val availableVehicle =
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
        vehicleId =
            testRestTemplate
                .postForEntity(ordersUrl, availableVehicle, String::class.java)
                .body
                .toString()
                .substringAfterLast("vehicleId\":\"")
                .substringBefore("\"")
    }

    @When("the customer submits the vehicle order form")
    fun `the customer submits the vehicle order form`() {
        requestInput = mapOf("vehicleId" to vehicleId, "customerId" to "1234567890")
        response = testRestTemplate.postForEntity(ordersUrl, requestInput, String::class.java)
    }

    @Then("the vehicle should be ordered successfully")
    fun `the vehicle should be ordered successfully`() {
        response.statusCode shouldBe HttpStatus.CREATED
    }

    @Given("the system has an invalid vehicle order form missing required fields")
    fun `the system has an invalid vehicle order form missing required fields`() {
        requestInput = mapOf("vehicleId" to vehicleId) // Missing customerId
    }

    @Then("the system should reject the order with a bad request error")
    fun `the system should reject the order with a bad request error`() {
        response.statusCode shouldBe HttpStatus.BAD_REQUEST
    }

    @Given("the system has a vehicle that is out of stock")
    fun `the system has a vehicle that is out of stock`() {
        val soldVehicle =
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
                "status" to "SOLD",
                "createdAt" to "2023-10-01T12:00:00Z",
            )
        vehicleId =
            testRestTemplate
                .postForEntity(ordersUrl, soldVehicle, String::class.java)
                .body
                .toString()
                .substringAfterLast("vehicleId\":\"")
                .substringBefore("\"")
    }

    @When("the customer attempts to order the vehicle")
    fun `the customer attempts to order the vehicle`() {
        requestInput = mapOf("vehicleId" to vehicleId, "customerId" to "1234567890")
        response = testRestTemplate.postForEntity(ordersUrl, requestInput, String::class.java)
    }

    @Then("the system should reject the order with an out of stock error")
    fun `the system should reject the order with an out of stock error`() {
        response.statusCode shouldBe HttpStatus.CONFLICT
        response.body shouldBe "The vehicle is out of stock and cannot be ordered."
    }
}
