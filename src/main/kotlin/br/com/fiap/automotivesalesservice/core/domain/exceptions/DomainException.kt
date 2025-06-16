package br.com.fiap.automotivesalesservice.core.domain.exceptions

open class DomainException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : Exception(message, cause)
