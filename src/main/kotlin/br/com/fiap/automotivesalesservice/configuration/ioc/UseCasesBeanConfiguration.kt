package br.com.fiap.automotivesalesservice.configuration.ioc

import br.com.fiap.automotivesalesservice.core.application.ports.driven.VehicleRepository
import br.com.fiap.automotivesalesservice.core.application.ports.driver.ListAvailableVehiclesDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.ListSoldVehiclesDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.SaveVehicleDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.UpdateVehicleDriverPort
import br.com.fiap.automotivesalesservice.core.application.useCases.ListAvailableVehiclesUseCase
import br.com.fiap.automotivesalesservice.core.application.useCases.ListSoldVehiclesUseCase
import br.com.fiap.automotivesalesservice.core.application.useCases.SaveVehicleUseCase
import br.com.fiap.automotivesalesservice.core.application.useCases.UpdateVehicleUseCase
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
}
