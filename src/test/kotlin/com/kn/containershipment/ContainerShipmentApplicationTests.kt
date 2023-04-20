package com.kn.containershipment

import com.kn.containershipment.controller.ExecutionPlanController
import com.kn.containershipment.model.*
import com.kn.containershipment.repository.ExecutionPlanActionRepository
import com.kn.containershipment.repository.ExecutionPlanRepository
import com.kn.containershipment.repository.TemperatureRangeRepository
import com.kn.containershipment.repository.TemplateRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@ExtendWith(MockitoExtension::class)
class ContainerShipmentApplicationTests {
	@Mock
	lateinit var executionPlanRepository: ExecutionPlanRepository

	@Mock
	lateinit var templateRepository: TemplateRepository

	@Mock
	lateinit var temperatureRangeRepository: TemperatureRangeRepository

	@Mock
	lateinit var executionPlanActionRepository: ExecutionPlanActionRepository

	@InjectMocks
	lateinit var executionPlanController: ExecutionPlanController

	@Test
	fun testCreateExecutionPlan() {
		// Given
		val shipment = Shipment(
			origin = "New York",
			destination = "London",
			customerId = "1234",
			transportType = TransportType.AIR,
			temperature = TemperatureRange(min = 10, max = 20),
			fragile = false,
			notifyCustomer = false
		)

		val template = PlanTemplate(
			id = 1,
			name = "General Shipment Template",
			actions = listOf(Action(id = 1, name = "Action 1"), Action(id = 2, name = "Action 2")),
			temperatureRange = TemperatureRange(id = 1, min = 10, max = 20)
		)

		val actions = template.actions.map { ExecutionPlanAction(actionName = it.name) }

		val executionPlan = ExecutionPlan(
			origin = shipment.origin,
			destination = shipment.destination,
			customerId = shipment.customerId,
			transportType = shipment.transportType,
			temperature = template.temperatureRange,
			fragile = shipment.fragile,
			notifyCustomer = shipment.notifyCustomer,
			templateId = template.id,
			actions = actions
		)

		val savedExecutionPlan = ExecutionPlan(
			id = 1,
			origin = shipment.origin,
			destination = shipment.destination,
			customerId = shipment.customerId,
			transportType = shipment.transportType,
			temperature = template.temperatureRange,
			fragile = shipment.fragile,
			notifyCustomer = shipment.notifyCustomer,
			templateId = template.id,
			actions = actions
		)

		Mockito.`when`(templateRepository.findByName(anyString())).thenReturn(template)
		Mockito.`when`(temperatureRangeRepository.findByMinAndMax(anyInt(), anyInt())).thenReturn(template.temperatureRange)
		Mockito.`when`(executionPlanActionRepository.saveAll(actions)).thenReturn(actions)
		Mockito.`when`(executionPlanRepository.save(executionPlan)).thenReturn(savedExecutionPlan)

		// When
		val responseEntity: ResponseEntity<ExecutionPlan> = executionPlanController.createExecutionPlan(shipment)

		// Then
		assertEquals(HttpStatus.CREATED, responseEntity.statusCode)
		assertEquals(savedExecutionPlan, responseEntity.body)
	}
}