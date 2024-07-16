package com.rud.tickets_search_domain.usecase

import com.rud.tickets_search_domain.repository.TicketsRecommendationsRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTicketsRecommendationsUseCase @Inject constructor(
    private val ticketsRecommendationsRepository: TicketsRecommendationsRepository
) {
    operator fun invoke() = flow{
        emitAll(ticketsRecommendationsRepository.getTicketsRecommendations())
    }
}