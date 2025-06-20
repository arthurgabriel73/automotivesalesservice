package br.com.fiap.automotivesalesservice.adapters.driven.api

import br.com.fiap.automotivesalesservice.adapters.driven.api.dto.SendOrderRequest

interface SalesHubApi {
    fun sendOrder(request: SendOrderRequest): Boolean
}
