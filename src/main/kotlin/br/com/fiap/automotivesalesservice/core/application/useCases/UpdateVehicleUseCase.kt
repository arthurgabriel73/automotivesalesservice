package br.com.fiap.automotivesalesservice.core.application.useCases

import br.com.fiap.automotivesalesservice.core.application.ports.driven.VehicleRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driver.UpdateVehicleDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.UpdateVehicleInput
import br.com.fiap.automotivesalesservice.core.application.useCases.exceptions.VehicleNotFoundException
import br.com.fiap.automotivesalesservice.core.domain.vehicle.Vehicle
import java.util.*

class UpdateVehicleUseCase(val vehicleRepository: VehicleRepository) : UpdateVehicleDriverPort {
    override fun execute(input: UpdateVehicleInput) {
        val vehicle = getVehicleOrFail(input.vehicleId)
        val vehicleUpdatedData =
            Vehicle(
                vehicleId = vehicle.vehicleId,
                make = input.make,
                model = input.model,
                version = input.version,
                yearFabrication = input.yearFabrication,
                yearModel = input.yearModel,
                kilometers = input.kilometers,
                color = input.color,
                plate = input.plate,
                price = input.price,
                priceCurrency = input.priceCurrency,
                status = input.status,
                createdAt = vehicle.createdAt,
                updatedAt = vehicle.updatedAt,
            )
        vehicleRepository.update(vehicleUpdatedData)
    }

    private fun getVehicleOrFail(id: String): Vehicle {
        val vehicle = vehicleRepository.findById(UUID.fromString(id))
        if (vehicle == null) throw VehicleNotFoundException("Vehicle with ID $id does not exist.")
        return vehicle
    }
}
