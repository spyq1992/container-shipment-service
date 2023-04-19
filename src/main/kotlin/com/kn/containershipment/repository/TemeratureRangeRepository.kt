package com.kn.containershipment.repository

import com.kn.containershipment.model.*
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TemperatureRangeRepository : CrudRepository<TemperatureRange, Long>{
    @Query("select * from temperature_range where min = :min and max= :max", nativeQuery = true)
    fun findByMinAndMax(min: Int, max: Int): TemperatureRange

}