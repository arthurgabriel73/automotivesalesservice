package br.com.fiap.automotivesalesservice.configuration

import org.springframework.http.HttpStatus

data class ErrorMessage(val status: HttpStatus, val error: String)
