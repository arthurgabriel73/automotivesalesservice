package br.com.fiap.automotivesalesservice.core.application.ports.driver

import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.SaveVehicleInput

interface SaveVehicleDriverPort {
    fun execute(input: SaveVehicleInput)
}
