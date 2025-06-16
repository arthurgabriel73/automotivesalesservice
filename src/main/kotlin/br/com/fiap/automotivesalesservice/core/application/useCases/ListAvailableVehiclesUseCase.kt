package br.com.fiap.automotivesalesservice.core.application.useCases

import br.com.fiap.automotivesalesservice.core.application.ports.driven.VehicleRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driver.ListAvailableVehiclesDriverPort
import br.com.fiap.automotivesalesservice.core.domain.vehicle.Vehicle

class ListAvailableVehiclesUseCase(val vehicleRepository: VehicleRepository) :
    ListAvailableVehiclesDriverPort {
    override fun execute(): List<Vehicle> {
        return vehicleRepository.listAvailableVehicles()
    }
}
