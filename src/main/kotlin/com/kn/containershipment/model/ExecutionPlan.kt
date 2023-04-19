package com.kn.containershipment.model

import jakarta.persistence.*

/**
 * ExecutionPlan is used to store information about shipment and its corresponding actions to be executed.
 */

@Entity
@Table(name = "execution_plan")
data class ExecutionPlan(

    @Id
    @SequenceGenerator(name = "execution_plan_generator", sequenceName = "execution_plan_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "execution_plan_generator")
    val id: Long = 0,

    val origin: String? = null,

    val destination: String? = null,

    val customerId: String? = null,

    @Enumerated(EnumType.STRING)
    val transportType: TransportType? = null,

    @OneToOne
    @JoinColumn(name = "temperature_id")
    val temperature: TemperatureRange? = null,

    val fragile: Boolean = false,

    val notifyCustomer: Boolean = false,

    @Column(name = "template_id")
    val templateId : Long,

    @OneToMany(mappedBy = "executionPlanId", cascade = [CascadeType.ALL], orphanRemoval = true)
    val actions: List<ExecutionPlanAction> = mutableListOf()
)

/**
 * ExecutionPlanAction is used to execution individual actions from the template actions in an ExecutionPlan
 */

