package com.rud.tickets_search_data.remote.repository

import com.rud.common.Resource
import com.rud.tickets_search_domain.model.TicketOffer
import com.rud.tickets_search_domain.repository.TicketsOffersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class FAKETicketsOffersRepositoryImpl: TicketsOffersRepository {

    private val ticketOffers = listOf(
        TicketOffer(
            id = 1,
            "Die Antwood",
            "Будапешт",
            22264
        ),
        TicketOffer(
            id = 2,
            "Socrat& Lera",
            "Санкт-Петербург",
            2390
        )
    )
    override suspend fun getTicketsOffers(): Flow<Resource<List<TicketOffer>>> = flow {
        emit(Resource.Success(ticketOffers))
    }
}