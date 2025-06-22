package br.com.fiap.automotivesalesservice.core.application.useCases

import br.com.fiap.automotivesalesservice.core.application.ports.driven.OrderRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driver.NotifyPaymentDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.NotifyPaymentInput
import br.com.fiap.automotivesalesservice.core.application.useCases.exceptions.OrderNotFoundException
import br.com.fiap.automotivesalesservice.core.domain.order.OrderStatus

class NotifyPaymentUseCase(private val orderRepository: OrderRepository) : NotifyPaymentDriverPort {
    override fun notifyPayment(input: NotifyPaymentInput) {
        val newOrderStatus =
            when (input.paymentStatus) {
                "APPROVED" -> OrderStatus.APPROVED
                else -> OrderStatus.REJECTED
            }
        var order =
            orderRepository.findById(input.orderId)
                ?: throw OrderNotFoundException("Order not found with ID: ${input.orderId}")

        order = order.copy(status = newOrderStatus)

        orderRepository.update(order)
    }
}
