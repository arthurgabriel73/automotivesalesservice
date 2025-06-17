package br.com.fiap.automotivesalesservice.core.application.useCases

import br.com.fiap.automotivesalesservice.core.application.ports.driven.OrderRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driven.VehicleRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driver.CreateOrderDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.CreateOrderInput
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.output.CreateOrderOutput
import br.com.fiap.automotivesalesservice.core.application.useCases.exceptions.VehicleNotAvailableException
import br.com.fiap.automotivesalesservice.core.domain.order.Order
import br.com.fiap.automotivesalesservice.core.domain.order.OrderStatus
import java.time.Instant
import java.util.*

class CreateOrderUseCase(
    private val orderRepository: OrderRepository,
    private val vehicleRepository: VehicleRepository,
) : CreateOrderDriverPort {
    override fun execute(input: CreateOrderInput): CreateOrderOutput {
        requireVehicleIsAvailable(input.vehicleId)
        val order =
            Order(
                orderId = UUID.randomUUID(),
                vehicleId = input.vehicleId,
                customerId = input.customerId,
                status = OrderStatus.PROCESSING,
                createdAt = Instant.now(),
            )

        val orderId = orderRepository.save(order)
        return CreateOrderOutput(orderId = orderId)
    }

    private fun requireVehicleIsAvailable(vehicleId: UUID) {
        val vehicle = vehicleRepository.findById(vehicleId)
        if (vehicle == null || vehicle.status != "AVAILABLE") {
            throw VehicleNotAvailableException(
                "Vehicle with ID $vehicleId is not available for order."
            )
        }
    }
}
