package com.rud.domain.usecase

import com.rud.domain.repository.DataStoreRepository

class SaveDepartureUseCase(val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(departure: String) = dataStoreRepository.saveDeparture(departure)
}