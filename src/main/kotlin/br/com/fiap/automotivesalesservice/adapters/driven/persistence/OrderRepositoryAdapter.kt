package br.com.fiap.automotivesalesservice.adapters.driven.persistence

import br.com.fiap.automotivesalesservice.core.application.ports.driven.OrderRepository
import br.com.fiap.automotivesalesservice.core.domain.order.Order
import jakarta.inject.Named
import java.util.*

@Named
class OrderRepositoryAdapter(private val jpaRepository: JpaOrderRepository) : OrderRepository {
    override fun save(order: Order): UUID {
        return jpaRepository.save(OrderEntity.fromDomain(order)).toDomain().orderId
    }

    override fun update(order: Order) {
        jpaRepository.save(OrderEntity.fromDomain(order)).toDomain()
    }
}
