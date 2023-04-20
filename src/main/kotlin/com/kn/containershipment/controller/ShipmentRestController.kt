package com.kn.containershipment.controller

import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.repository.*
import com.kn.containershipment.service.ExecutionPlanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/shipment")
class ShipmentRestController(
    @Autowired val executionPlanService: ExecutionPlanService
) {

    @GetMapping("/shipments")
    fun getShipments(): MutableIterable<Shipment> {
        return executionPlanService.getShipments()
    }

    @GetMapping("/templates")
    fun getPlanTemplates(): MutableIterable<PlanTemplate> {
        return executionPlanService.getPlanTemplates()
    }

//    @PostMapping("/execution-plans")
//    fun createExecutionPlan(@RequestBody request: ExecutionPlanRequest): ResponseEntity<ExecutionPlan> {
//        val shipment = shipmentRepository.findById(request.id)
//        val planTemplate = templateRepository.findById(request.templateId)
//        val template = templateRepository.findById(request.templateId)
//
//
//        val temperatureRange = shipment?.get()?.temperature?.let { temperatureRangeRepository.findByMinAndMax(shipment?.get()?.temperature!!.min, shipment?.get()?.temperature!!.max) }
//
//
//        val actions = template?.get()?.actions?.map { ExecutionPlanAction(actionName = it.name) }
//
//        executionPlanActionRepository.saveAll(actions)
//
//        val executionPlan = ExecutionPlan(
//            origin = shipment.origin,
//            destination = shipment.destination,
//            customerId = shipment.customerId,
//            transportType = shipment.transportType,
//            temperature = temperatureRange,
//            fragile = shipment.fragile,
//            notifyCustomer = shipment.notifyCustomer,
//            templateId = template.id,
//            actions = actions
//        )
//        val result = executionPlanRepository.save(executionPlan)
//
//
//        for (action in actions){
//            action.executionPlanId = result.id
//        }
//
//        executionPlanActionRepository.saveAll(actions)
//        return executionPlanRepository.save(executionPlan)
//    }

    @GetMapping("/execution-plans")
    fun getExecutionPlans(): MutableIterable<ExecutionPlan> {
        return executionPlanService.getExecutionPlans()
    }
}
