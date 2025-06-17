package br.com.fiap.automotivesalesservice.core.domain.order

import java.time.Instant
import java.util.UUID

data class Order(
    val orderId: UUID,
    val vehicleId: UUID,
    val customerId: String,
    val status: OrderStatus,
    val createdAt: Instant,
    val updatedAt: Instant? = null,
)
