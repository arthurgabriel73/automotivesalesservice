package br.com.fiap.automotivesalesservice.acceptance.steps

import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ListAvailableVehiclesAcceptanceTest {
    private val vehiclesUrl = "/vehicles"

    @Autowired private lateinit var testRestTemplate: TestRestTemplate

    @Autowired private lateinit var jdbcTemplate: JdbcTemplate

    private lateinit var response: ResponseEntity<String>

    companion object {
        @Container @ServiceConnection val postgres = PostgreSQLContainer("postgres:16.3")
    }

    @Before
    fun cleanVehiclesTable() {
        jdbcTemplate.execute("DELETE FROM vehicles")
    }

    @Given("the system has multiple vehicles available for order")
    fun `the system has multiple vehicles available for order`() {
        createVehicles(
            listOf(
                vehicleRequest(price = 30000L, make = "Fiat", model = "Uno", status = "AVAILABLE"),
                vehicleRequest(
                    price = 25000L,
                    make = "Ford",
                    model = "Fiesta",
                    status = "AVAILABLE",
                ),
                vehicleRequest(
                    price = 28000L,
                    make = "Volkswagen",
                    model = "Gol",
                    status = "AVAILABLE",
                ),
            )
        )
    }

    @When("the customer requests the list of available vehicles")
    fun `the customer requests the list of available vehicles`() {
        response = testRestTemplate.getForEntity("$vehiclesUrl/available", String::class.java)
    }

    @Then(
        "the system should return a list of all available vehicles ordered by price in ascending order"
    )
    fun `the system should return a list of all available vehicles ordered by price in ascending order`() {
        response.statusCode shouldBe HttpStatus.OK
        response.body!!.let { body ->
            body shouldContain "Fiat"
            body shouldContain "Ford"
            body shouldContain "Volkswagen"
            extractPrices(body) shouldBe extractPrices(body).sorted()
        }
    }

    @Given("the system has no vehicles available for order")
    fun `the system has no vehicles available for order`() {
        createVehicles(
            listOf(vehicleRequest(price = 30000L, make = "Fiat", model = "Uno", status = "SOLD"))
        )
    }

    @Then("the system should return an empty available vehicles list")
    fun `the system should return an empty list`() {
        response.statusCode shouldBe HttpStatus.OK
        response.body shouldBe "[]"
    }

    private fun createVehicles(requests: List<Map<String, Any>>) {
        requests.forEach { request ->
            testRestTemplate.postForEntity(vehiclesUrl, request, String::class.java)
        }
    }

    private fun vehicleRequest(
        price: Long,
        make: String,
        model: String,
        status: String,
        kilometers: Int = 15000,
        color: String = "Red",
        version: String = "Base",
    ): Map<String, Any> =
        mapOf(
            "vehicleId" to UUID.randomUUID().toString(),
            "make" to make,
            "model" to model,
            "version" to version,
            "yearFabrication" to "2020",
            "yearModel" to "2021",
            "kilometers" to kilometers,
            "color" to color,
            "plate" to "ABC-1234",
            "price" to price,
            "priceCurrency" to "BRL",
            "status" to status,
            "createdAt" to "2023-10-01T12:00:00Z",
        )

    private fun extractPrices(json: String): List<Long> {
        return json.split("},").map {
            it.substringAfter("\"price\":").substringBefore(",").trim().toLong()
        }
    }
}
