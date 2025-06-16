package br.com.fiap.automotivesalesservice.adapters.driver.rest

import br.com.fiap.automotivesalesservice.core.application.ports.driver.ListAvailableVehiclesDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.ListSoldVehiclesDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.SaveVehicleDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.UpdateVehicleDriverPort
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.SaveVehicleInput
import br.com.fiap.automotivesalesservice.core.application.ports.driver.models.input.UpdateVehicleInput
import br.com.fiap.automotivesalesservice.core.domain.vehicle.Vehicle
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vehicles")
class VehicleController(
    private val saveVehicleDriverPort: SaveVehicleDriverPort,
    private val listAvailableVehiclesDriverPort: ListAvailableVehiclesDriverPort,
    private val listSoldVehiclesDriverPort: ListSoldVehiclesDriverPort,
    private val updateVehicleDriverPort: UpdateVehicleDriverPort,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun saveVehicle(@RequestBody input: SaveVehicleInput) {
        saveVehicleDriverPort.execute(input)
    }

    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    fun listAvailableVehicles(): List<Vehicle> {
        return listAvailableVehiclesDriverPort.execute()
    }

    @GetMapping("/sold")
    @ResponseStatus(HttpStatus.OK)
    fun listSoldVehicles(): List<Vehicle> {
        return listSoldVehiclesDriverPort.execute()
    }

    @PutMapping("/update/{vehicleId}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    fun updateVehicle(
        @PathVariable(name = "vehicleId", required = true) vehicleId: String,
        @Validated @RequestBody input: UpdateVehicleInput,
    ) {
        return updateVehicleDriverPort.execute(input)
    }
}
