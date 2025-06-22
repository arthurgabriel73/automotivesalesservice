package br.com.fiap.automotivesalesservice.configuration

import br.com.fiap.automotivesalesservice.core.application.useCases.exceptions.OrderNotFoundException
import br.com.fiap.automotivesalesservice.core.application.useCases.exceptions.VehicleNotAvailableException
import br.com.fiap.automotivesalesservice.core.application.useCases.exceptions.VehicleNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class CustomExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(VehicleNotFoundException::class)
    fun handleVehicleNotFoundException(
        exception: VehicleNotFoundException
    ): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(status = HttpStatus.NOT_FOUND, error = exception.message)
        return ResponseEntity(errorMessage, HttpHeaders(), errorMessage.status)
    }

    @ExceptionHandler(VehicleNotAvailableException::class)
    fun handleVehicleNotAvailableException(
        exception: VehicleNotAvailableException
    ): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(status = HttpStatus.CONFLICT, error = exception.message)
        return ResponseEntity(errorMessage, HttpHeaders(), errorMessage.status)
    }

    @ExceptionHandler(OrderNotFoundException::class)
    fun handleOrderNotFoundException(
        exception: OrderNotFoundException
    ): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(status = HttpStatus.NOT_FOUND, error = exception.message)
        return ResponseEntity(errorMessage, HttpHeaders(), errorMessage.status)
    }
}
