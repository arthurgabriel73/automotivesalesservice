package br.com.fiap.automotivesalesservice.core.application.ports.driven

import br.com.fiap.automotivesalesservice.core.domain.order.Order

interface SalesHubService {
    fun sendOrder(order: Order): Boolean
}
