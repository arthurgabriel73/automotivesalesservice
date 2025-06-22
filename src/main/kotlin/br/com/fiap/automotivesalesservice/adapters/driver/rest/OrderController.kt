package br.com.fiap.automotivesalesservice.adapters.driver.rest

import br.com.fiap.automotivesalesservice.core.application.ports.driver.CreateOrderDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.NotifyPaymentDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.CreateOrderInput
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.NotifyPaymentInput
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.output.CreateOrderOutput
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    private val createOrderDriverPort: CreateOrderDriverPort,
    private val notifyPaymentDriverPort: NotifyPaymentDriverPort,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@Validated @RequestBody input: CreateOrderInput): CreateOrderOutput {
        return createOrderDriverPort.execute(input)
    }

    @PostMapping("/notify-payment/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    fun notifyPayment(
        @PathVariable(name = "orderId", required = true) orderId: String,
        @RequestBody input: NotifyPaymentInput,
    ) {
        notifyPaymentDriverPort.notifyPayment(input)
    }
}
