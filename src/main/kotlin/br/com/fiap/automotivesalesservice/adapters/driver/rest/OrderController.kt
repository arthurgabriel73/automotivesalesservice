package br.com.fiap.automotivesalesservice.adapters.driver.rest

import br.com.fiap.automotivesalesservice.core.application.ports.driver.CreateOrderDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.CreateOrderInput
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.output.CreateOrderOutput
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(private val createOrderDriverPort: CreateOrderDriverPort) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun createOrder(@Validated @RequestBody input: CreateOrderInput): CreateOrderOutput {
        return createOrderDriverPort.execute(input)
    }
}
