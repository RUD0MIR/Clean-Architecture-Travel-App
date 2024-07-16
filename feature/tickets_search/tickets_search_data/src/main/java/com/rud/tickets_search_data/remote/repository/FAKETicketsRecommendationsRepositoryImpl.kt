package com.rud.tickets_search_data.remote.repository

import com.rud.common.Resource
import com.rud.tickets_search_domain.model.TicketRecommendation
import com.rud.tickets_search_domain.repository.TicketsRecommendationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FAKETicketsRecommendationsRepositoryImpl: TicketsRecommendationsRepository {
    private val ticketRecommendations = listOf(
        TicketRecommendation(
            id = 1,
            price = 2390,
            timeRange = listOf("07:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00"),
            title = "Уральские авиалинии"
        ),
        TicketRecommendation(
            id = 2,
            price = 2390,
            timeRange = listOf("08:05", "09:55", "16:35", "18:55"),
            title = "Победа"
        ),
        TicketRecommendation(
            id = 3,
            price = 2390,
            timeRange = listOf("13:10"),
            title = "NordStar"
        ),
    )
    override suspend fun getTicketsRecommendations(): Flow<Resource<List<TicketRecommendation>>> = flow {
        emit(Resource.Success(ticketRecommendations))
    }
}