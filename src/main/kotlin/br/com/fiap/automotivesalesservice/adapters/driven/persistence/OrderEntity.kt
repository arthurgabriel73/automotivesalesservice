package br.com.fiap.automotivesalesservice.adapters.driven.persistence

import br.com.fiap.automotivesalesservice.core.domain.order.Order
import br.com.fiap.automotivesalesservice.core.domain.order.OrderStatus
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id val orderId: UUID,
    val vehicleId: UUID,
    val customerId: String,
    @Enumerated(EnumType.STRING) val status: OrderStatus,
    val createdAt: Instant,
    val updatedAt: Instant? = null,
) {
    fun toDomain(): Order {
        return Order(
            orderId = orderId,
            vehicleId = vehicleId,
            customerId = customerId,
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    companion object {
        fun fromDomain(order: Order): OrderEntity {
            return OrderEntity(
                orderId = order.orderId,
                vehicleId = order.vehicleId,
                customerId = order.customerId,
                status = order.status,
                createdAt = order.createdAt,
                updatedAt = order.updatedAt,
            )
        }
    }
}
