package br.com.fiap.automotivesalesservice.core.application.ports.driver

import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.UpdateVehicleInput

interface UpdateVehicleDriverPort {
    fun execute(input: UpdateVehicleInput)
}
