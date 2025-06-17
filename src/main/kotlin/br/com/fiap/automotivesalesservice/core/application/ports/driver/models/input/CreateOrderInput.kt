package br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input

import java.util.*

data class CreateOrderInput(val vehicleId: UUID, val customerId: String)
