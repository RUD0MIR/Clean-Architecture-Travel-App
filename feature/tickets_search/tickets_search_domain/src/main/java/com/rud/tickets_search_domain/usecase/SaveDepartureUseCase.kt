package com.rud.tickets_search_domain.usecase

import com.rud.tickets_search_domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveDepartureUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(departure: String) = dataStoreRepository.saveDeparture(departure)
}