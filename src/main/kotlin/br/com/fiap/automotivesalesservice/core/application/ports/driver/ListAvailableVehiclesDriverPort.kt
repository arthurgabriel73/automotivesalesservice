package br.com.fiap.automotivesalesservice.core.application.ports.driver

import br.com.fiap.automotivesalesservice.core.domain.vehicle.Vehicle

interface ListAvailableVehiclesDriverPort {
    fun execute(): List<Vehicle>
}
