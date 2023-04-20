package com.kn.containershipment.controller

import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.service.ExecutionPlanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/execution-plans")
class ExecutionPlanController(@Autowired val executionPlanService: ExecutionPlanService) {

    @PostMapping
    fun createExecutionPlan(@RequestBody shipment: Shipment): ResponseEntity<ExecutionPlan> {
        val result = executionPlanService.createExecutionPlan(shipment)

        return ResponseEntity.status(HttpStatus.CREATED).body(result)
    }


}
