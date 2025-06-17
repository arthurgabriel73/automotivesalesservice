package br.com.fiap.automotivesalesservice.core.application.ports.driver

import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.CreateOrderInput
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.output.CreateOrderOutput

interface CreateOrderDriverPort {
    fun execute(input: CreateOrderInput): CreateOrderOutput
}
