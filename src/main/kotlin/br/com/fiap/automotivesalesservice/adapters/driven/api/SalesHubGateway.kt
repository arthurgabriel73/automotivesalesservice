package br.com.fiap.automotivesalesservice.adapters.driven.api

import br.com.fiap.automotivesalesservice.adapters.driven.api.dto.SendOrderRequest
import br.com.fiap.automotivesalesservice.core.application.ports.driven.SalesHubService
import br.com.fiap.automotivesalesservice.core.domain.order.Order
import jakarta.inject.Named

@Named
class SalesHubGateway(private val salesHubServiceApi: SalesHubApi) : SalesHubService {
    override fun sendOrder(order: Order): Boolean {
        return salesHubServiceApi.sendOrder(SendOrderRequest(order))
    }
}
