package br.com.fiap.automotivesalesservice.core.application.useCases

import br.com.fiap.automotivesalesservice.core.application.ports.driven.VehicleRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driver.SaveVehicleDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.SaveVehicleInput
import br.com.fiap.automotivesalesservice.core.domain.vehicle.Vehicle
import java.util.*

class SaveVehicleUseCase(val vehicleRepository: VehicleRepository) : SaveVehicleDriverPort {
    override fun execute(input: SaveVehicleInput) {
        val vehicle =
            Vehicle(
                vehicleId = UUID.fromString(input.vehicleId),
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
                createdAt = input.createdAt,
                updatedAt = input.updatedAt,
            )
        vehicleRepository.save(vehicle)
    }
}
