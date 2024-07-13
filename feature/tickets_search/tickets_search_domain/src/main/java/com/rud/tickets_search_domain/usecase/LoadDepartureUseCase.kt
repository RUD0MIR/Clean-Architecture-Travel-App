package com.rud.tickets_search_domain.usecase

import com.rud.tickets_search_domain.repository.DataStoreRepository
import javax.inject.Inject

class LoadDepartureUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() = dataStoreRepository.loadDeparture()
}