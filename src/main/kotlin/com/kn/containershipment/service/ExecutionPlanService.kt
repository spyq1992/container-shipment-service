package com.kn.containershipment.service

import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.ExecutionPlanAction
import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExecutionPlanService (
    @Autowired val executionPlanRepository: ExecutionPlanRepository, @Autowired val templateRepository: TemplateRepository, @Autowired val temperatureRangeRepository: TemperatureRangeRepository, @Autowired val executionPlanActionRepository: ExecutionPlanActionRepository, @Autowired val shipmentRepository: ShipmentRepository
) {
    fun createExecutionPlan(shipment: Shipment): ExecutionPlan {
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
        return result
    }

    fun getShipments(): MutableIterable<Shipment> {
        return shipmentRepository.findAll()
    }

    fun getPlanTemplates(): MutableIterable<PlanTemplate> {
        return templateRepository.findAll()
    }

    fun getExecutionPlans(): MutableIterable<ExecutionPlan> {
        return executionPlanRepository.findAll()
    }
}