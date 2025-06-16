package br.com.fiap.automotivesalesservice.core.application.useCases

import br.com.fiap.automotivesalesservice.core.application.ports.driven.VehicleRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driver.ListSoldVehiclesDriverPort
import br.com.fiap.automotivesalesservice.core.domain.vehicle.Vehicle

class ListSoldVehiclesUseCase(val vehicleRepository: VehicleRepository) :
    ListSoldVehiclesDriverPort {
    override fun execute(): List<Vehicle> {
        return vehicleRepository.listSoldVehicles()
    }
}
