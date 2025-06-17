package br.com.fiap.automotivesalesservice.configuration.ioc

import br.com.fiap.automotivesalesservice.core.application.ports.driven.OrderRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driven.VehicleRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driver.*
import br.com.fiap.automotivesalesservice.core.application.useCases.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCasesBeanConfiguration {

    @Bean
    fun saveVehicleUseCase(vehicleRepository: VehicleRepository): SaveVehicleDriverPort {
        return SaveVehicleUseCase(vehicleRepository)
    }

    @Bean
    fun listAvailableVehiclesUseCase(
        vehicleRepository: VehicleRepository
    ): ListAvailableVehiclesDriverPort {
        return ListAvailableVehiclesUseCase(vehicleRepository)
    }

    @Bean
    fun listSoldVehiclesUseCase(vehicleRepository: VehicleRepository): ListSoldVehiclesDriverPort {
        return ListSoldVehiclesUseCase(vehicleRepository)
    }

    @Bean
    fun updateVehicleUseCase(vehicleRepository: VehicleRepository): UpdateVehicleDriverPort {
        return UpdateVehicleUseCase(vehicleRepository)
    }

    @Bean
    fun createOrderUseCase(
        orderRepository: OrderRepository,
        vehicleRepository: VehicleRepository,
    ): CreateOrderDriverPort {
        return CreateOrderUseCase(orderRepository, vehicleRepository)
    }
}
