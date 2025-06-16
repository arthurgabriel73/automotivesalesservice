package br.com.fiap.automotivesalesservice.adapters.driven.persistence

import br.com.fiap.automotivesalesservice.core.domain.vehicle.Vehicle
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Type
import java.util.UUID

@Entity
@Table(name = "vehicles")
data class VehicleEntity (
    @Id
    val vehicleId: UUID,
    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    val data: Vehicle,
    val price: Int,
    val status: String,
) {
    fun toDomain(): Vehicle {
        return Vehicle(
            vehicleId = vehicleId,
            make = data.make,
            model = data.model,
            version = data.version,
            yearFabrication = data.yearFabrication,
            yearModel = data.yearModel,
            kilometers = data.kilometers,
            color = data.color,
            plate = data.plate,
            price = price,
            priceCurrency = data.priceCurrency,
            status = status,
            createdAt = data.createdAt,
            updatedAt = data.updatedAt
        )
    }


    companion object {
        fun fromDomain(vehicle: Vehicle): VehicleEntity {
            return VehicleEntity(
                vehicleId = vehicle.vehicleId,
                data = vehicle,
                price = vehicle.price,
                status = vehicle.status,
            )
        }
    }
}
