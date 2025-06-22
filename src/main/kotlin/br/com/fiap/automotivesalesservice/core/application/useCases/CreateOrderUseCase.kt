package br.com.fiap.automotivesalesservice.core.application.useCases

import br.com.fiap.automotivesalesservice.core.application.ports.driven.OrderRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driven.SalesHubService
import br.com.fiap.automotivesalesservice.core.application.ports.driver.CreateOrderDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.CreateOrderInput
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.output.CreateOrderOutput
import br.com.fiap.automotivesalesservice.core.domain.order.Order
import br.com.fiap.automotivesalesservice.core.domain.order.OrderStatus
import java.time.Instant
import java.util.*

class CreateOrderUseCase(
    private val orderRepository: OrderRepository,
    private val salesHubService: SalesHubService,
) : CreateOrderDriverPort {
    override fun execute(input: CreateOrderInput): CreateOrderOutput {
        var order =
            Order(
                orderId = UUID.randomUUID(),
                vehicleId = input.vehicleId,
                customerId = input.customerId,
                status = OrderStatus.PROCESSING,
                createdAt = Instant.now(),
            )

        val orderResult = sendOrderToSalesHubService(order)
        if (!orderResult) order = order.copy(status = OrderStatus.REJECTED)
        val orderId = orderRepository.save(order)
        return CreateOrderOutput(orderId = orderId)
    }

    private fun sendOrderToSalesHubService(order: Order): Boolean {
        return salesHubService.sendOrder(order)
    }
}
