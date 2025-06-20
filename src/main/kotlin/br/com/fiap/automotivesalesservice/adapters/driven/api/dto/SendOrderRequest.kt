package br.com.fiap.automotivesalesservice.adapters.driven.api.dto

import br.com.fiap.automotivesalesservice.core.domain.order.Order

data class SendOrderRequest(private val order: Order) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "orderId" to order.orderId.toString(),
            "vehicleId" to order.vehicleId.toString(),
        )
    }
}
