package com.kn.containershipment.model

import jakarta.persistence.*

@Entity
@Table(name = "plan_template")
data class PlanTemplate(

    @Id
    val id: Long = 0,

    val name: String? = null,

    @OneToMany(mappedBy = "templateId", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val actions: List<Action> = mutableListOf(),

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "fk_temperature_range_id")
    val temperatureRange: TemperatureRange? = null
)

