package br.com.fiap.automotivesalesservice.core.domain.vehicle

import java.util.UUID

data class Vehicle(
    val vehicleId: UUID,
    val make: String,
    val model: String,
    val version: String,
    val yearFabrication: String,
    val yearModel: String,
    val kilometers: Int,
    val color: String,
    val plate: String,
    val price: Int,
    val priceCurrency: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String? = null,
)
