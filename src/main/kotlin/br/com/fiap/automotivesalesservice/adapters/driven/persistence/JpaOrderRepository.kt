package br.com.fiap.automotivesalesservice.adapters.driven.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface JpaOrderRepository : JpaRepository<OrderEntity, UUID>
