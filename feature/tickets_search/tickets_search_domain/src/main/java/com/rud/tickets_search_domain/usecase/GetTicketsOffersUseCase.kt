package com.rud.tickets_search_domain.usecase

import com.rud.tickets_search_domain.repository.TicketsOffersRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTicketsOffersUseCase @Inject constructor(
    private val offersRepository: TicketsOffersRepository
) {
    operator fun invoke() = flow{
        emitAll(offersRepository.getTicketsOffers())
    }
}