package com.kn.containershipment.model

import jakarta.persistence.*

@Entity
@Table(name = "action")
data class Action(
    @Id
    val id: Long = 0,
    val name: String? = null,

    var templateId: Long = 0
)

