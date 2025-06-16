package br.com.fiap.automotivesalesservice.adapters.driven.persistence

import br.com.fiap.automotivesalesservice.core.application.ports.driven.VehicleRepository
import br.com.fiap.automotivesalesservice.core.domain.vehicle.Vehicle
import java.util.UUID

class VehicleRepositoryAdapter(private val jpaRepository: JpaVehicleRepository) : VehicleRepository {
    override fun save(vehicle: Vehicle) {
        val entity = VehicleEntity.fromDomain(vehicle)
        jpaRepository.save(entity)
    }

    override fun findById(id: UUID): Vehicle? {
        return jpaRepository.findByVehicleId(id)?.toDomain()
    }

    override fun listAvailableVehicles(): List<Vehicle> {
        return jpaRepository.findAvailableVehicles()
    }

    override fun listSoldVehicles(): List<Vehicle> {
        return jpaRepository.findSoldVehicles()
    }

    override fun update(vehicle: Vehicle) {
        val entity = VehicleEntity.fromDomain(vehicle)
        jpaRepository.save(entity)
    }
}
