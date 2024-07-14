package com.rud.tickets_search_domain.usecase

import com.rud.tickets_search_domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadDepartureUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke() = flow {
        emitAll(dataStoreRepository.loadDeparture())
    }
}