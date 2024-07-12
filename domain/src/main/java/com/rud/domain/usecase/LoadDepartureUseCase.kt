package com.rud.domain.usecase

import com.rud.domain.repository.DataStoreRepository

class LoadDepartureUseCase(val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke() = dataStoreRepository.loadDeparture()
}