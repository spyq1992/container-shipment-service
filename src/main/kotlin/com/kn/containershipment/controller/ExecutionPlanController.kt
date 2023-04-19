package com.kn.containershipment.controller

import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.ExecutionPlanAction
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.repository.ExecutionPlanActionRepository
import com.kn.containershipment.repository.ExecutionPlanRepository
import com.kn.containershipment.repository.TemperatureRangeRepository
import com.kn.containershipment.repository.TemplateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/execution-plans")
class ExecutionPlanController(@Autowired val executionPlanRepository: ExecutionPlanRepository, @Autowired val templateRepository: TemplateRepository, @Autowired val temperatureRangeRepository: TemperatureRangeRepository, @Autowired val executionPlanActionRepository: ExecutionPlanActionRepository) {

    @PostMapping
    fun createExecutionPlan(@RequestBody shipment: Shipment): ResponseEntity<ExecutionPlan> {
        val template = templateRepository.findByName("General Shipment Template")


        val temperatureRange =
            shipment.temperature?.let { temperatureRangeRepository.findByMinAndMax(shipment.temperature.min, shipment.temperature.max) }

        val actions = template.actions.map { ExecutionPlanAction(actionName = it.name) }

        executionPlanActionRepository.saveAll(actions)

        val executionPlan = ExecutionPlan(
            origin = shipment.origin,
            destination = shipment.destination,
            customerId = shipment.customerId,
            transportType = shipment.transportType,
            temperature = temperatureRange,
            fragile = shipment.fragile,
            notifyCustomer = shipment.notifyCustomer,
            templateId = template.id,
            actions = actions
        )
        val result = executionPlanRepository.save(executionPlan)


        for (action in actions){
            action.executionPlanId = result.id
        }

        executionPlanActionRepository.saveAll(actions)


        return ResponseEntity.status(HttpStatus.CREATED).body(result)
    }
}
