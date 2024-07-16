package com.rud.tickets_search_data.remote.repository

import com.rud.common.Resource
import com.rud.tickets_search_data.model.toTicketOffer
import com.rud.tickets_search_data.model.toTicketRecommendation
import com.rud.tickets_search_data.remote.TicketsApi
import com.rud.tickets_search_domain.model.TicketRecommendation
import com.rud.tickets_search_domain.repository.TicketsRecommendationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TicketsRecommendationsRepositoryImpl @Inject constructor(
    private val ticketsApi: TicketsApi
): TicketsRecommendationsRepository {
    override suspend fun getTicketsRecommendations(): Flow<Resource<List<TicketRecommendation>>> = flow {
        try {
            emit(Resource.Loading())
            val ticketsOffers = ticketsApi.getTicketsRecommendations().map { it.toTicketRecommendation() }
            emit(Resource.Success(ticketsOffers))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}