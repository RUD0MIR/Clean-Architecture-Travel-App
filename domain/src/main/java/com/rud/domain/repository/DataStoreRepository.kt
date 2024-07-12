package com.rud.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveDeparture(departure: String)
    suspend fun loadDeparture(): Flow<String>
}