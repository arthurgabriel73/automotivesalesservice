package br.com.fiap.automotivesalesservice.core.application.ports.driven

import br.com.fiap.automotivesalesservice.core.domain.order.Order
import java.util.*

interface OrderRepository {
    fun save(order: Order): UUID

    fun findById(orderId: UUID): Order?

    fun update(order: Order)
}
