package br.com.fiap.automotivesalesservice.adapters.driven.persistence

import br.com.fiap.automotivesalesservice.core.domain.vehicle.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface JpaVehicleRepository : JpaRepository<VehicleEntity, UUID> {
    fun findByVehicleId(vehicleId: UUID): VehicleEntity?

    @Query("SELECT v.data FROM VehicleEntity v WHERE v.status = 'AVAILABLE' ORDER BY v.price")
    fun findAvailableVehicles(): List<Vehicle>

    @Query("SELECT v.data FROM VehicleEntity v WHERE v.status = 'SOLD' ORDER BY v.price")
    fun findSoldVehicles(): List<Vehicle>
}
