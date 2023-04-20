package com.kn.containershipment.repository

import com.kn.containershipment.model.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ShipmentRepository : CrudRepository<Shipment, Long>