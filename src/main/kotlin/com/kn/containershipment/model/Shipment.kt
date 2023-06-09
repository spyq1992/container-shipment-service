package com.kn.containershipment.model

import jakarta.persistence.*
@Entity
@Table
data class Shipment(
    @Id
    @SequenceGenerator(name = "shipment_generator", sequenceName = "shipment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_generator")
    val id: Long = 0,

    val origin: String? = null,

    val destination: String? = null,

    val customerId: String? = null,

    val createdDate: Long = 0,

    val fragile: Boolean = false,

    val notifyCustomer: Boolean = false,

    @Enumerated(EnumType.STRING)
    val transportType: TransportType? = null,

    @OneToOne
    @JoinColumn(name = "temperature_id")
    val temperature: TemperatureRange? = null
)

enum class TransportType {
    AIR,
    SEA,
    ROAD
}

@Entity
@Table
data class TemperatureRange(
    @Id
    @SequenceGenerator(name = "temperature_range_generator", sequenceName = "temperature_range_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "temperature_range_generator")
    val id: Long = 0,
    val min: Int = 0,
    val max: Int = 0
)
