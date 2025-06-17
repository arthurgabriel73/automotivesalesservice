package br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input

data class SaveVehicleInput(
    val vehicleId: String,
    val plate: String,
    val price: Int,
    val priceCurrency: String,
    val make: String,
    val model: String,
    val version: String,
    val yearFabrication: String,
    val yearModel: String,
    val kilometers: Int,
    val color: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String? = null,
)
