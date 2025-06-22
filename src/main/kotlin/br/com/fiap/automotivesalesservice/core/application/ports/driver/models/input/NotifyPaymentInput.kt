package br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input

import java.util.*

data class NotifyPaymentInput(val orderId: UUID, val paymentStatus: String)
