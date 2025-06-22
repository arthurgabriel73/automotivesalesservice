package br.com.fiap.automotivesalesservice.core.application.ports.driver

import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.NotifyPaymentInput

interface NotifyPaymentDriverPort {
    fun notifyPayment(input: NotifyPaymentInput)
}
