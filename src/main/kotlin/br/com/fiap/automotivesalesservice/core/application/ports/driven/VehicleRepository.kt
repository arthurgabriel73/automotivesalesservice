package br.com.fiap.automotivesalesservice.core.application.ports.driven

import br.com.fiap.automotivesalesservice.core.domain.vehicle.Vehicle
import java.util.UUID

interface VehicleRepository {
    fun save(vehicle: Vehicle)
    fun findById(id: UUID): Vehicle?
    fun listAvailableVehicles(): List<Vehicle>
    fun listSoldVehicles(): List<Vehicle>
    fun update(vehicle: Vehicle)
}
