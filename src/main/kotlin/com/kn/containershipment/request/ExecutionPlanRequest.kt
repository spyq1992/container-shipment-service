package com.kn.containershipment.request

import com.kn.containershipment.model.TemperatureRange
import com.kn.containershipment.model.TransportType
data class ExecutionPlanRequest(

    val id: Long = 0,

    val origin: String? = null,

    val destination: String? = null,

    val customerId: String? = null,

    val createdDate: Long = 0,

    val fragile: Boolean = false,

    val notifyCustomer: Boolean = false,

    val transportType: TransportType? = null,

    val temperature: TemperatureRange? = null,

    val templateId: Long = 0,
)

