package com.kn.containershipment.model

import jakarta.persistence.*

@Entity
@Table(name = "execution_plan_action")
data class ExecutionPlanAction(

    @Id
    @SequenceGenerator(name = "execution_plan_action_generator", sequenceName = "execution_plan_action_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "execution_plan_action_generator")
    val id: Long = 0,

    val actionName: String? = null,

    val isExecuted: Boolean = true,

    val isNotify: Boolean = false,

    var executionPlanId: Long? = null
)

