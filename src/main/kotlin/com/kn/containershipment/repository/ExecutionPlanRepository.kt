package com.kn.containershipment.repository

import com.kn.containershipment.model.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ExecutionPlanRepository : CrudRepository<ExecutionPlan, Long>