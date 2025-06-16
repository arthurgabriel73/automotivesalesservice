package br.com.fiap.automotivesalesservice.core.application.useCases.exceptions

open class ApplicationException(
    override val message: String = "An application error occurred.",
    override val cause: Throwable? = null,
) : Exception(message, cause)

data class VehicleNotFoundException(
    override val message: String = "Vehicle not found.",
    override val cause: Throwable? = null,
) : ApplicationException(message, cause)
