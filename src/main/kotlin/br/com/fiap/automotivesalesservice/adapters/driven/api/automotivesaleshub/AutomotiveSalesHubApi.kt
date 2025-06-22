package br.com.fiap.automotivesalesservice.adapters.driven.api.automotivesaleshub

import br.com.fiap.automotivesalesservice.adapters.driven.api.SalesHubApi
import br.com.fiap.automotivesalesservice.adapters.driven.api.dto.SendOrderRequest
import jakarta.inject.Named
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.client.RestTemplate

@Named
class AutomotiveSalesHubApi() : SalesHubApi {

    private var restTemplate: RestTemplate = RestTemplate()

    @Value("\${automotive.sales.hub.url}") lateinit var baseUrl: String

    override fun sendOrder(request: SendOrderRequest): Boolean {
        try {
            val response =
                restTemplate.postForEntity(
                    "$baseUrl/v1/vehicles/purchase",
                    request.toMap(),
                    String::class.java,
                )
            return response.statusCode.is2xxSuccessful
        } catch (e: Exception) {
            return false
        }
    }
}
