package com.rud.tickets_search_data.remote.repository

import com.rud.common.Resource
import com.rud.tickets_search_data.remote.TicketsApi
import com.rud.tickets_search_data.toDomain
import com.rud.tickets_search_domain.model.TicketOffer
import com.rud.tickets_search_domain.repository.TicketsOffersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val TAG = "OffersRepositoryImpl"

internal class TicketsOffersRepositoryImpl @Inject constructor(
    private val ticketsApi: TicketsApi
) : TicketsOffersRepository {
    override suspend fun getTicketsOffers(): Flow<Resource<List<TicketOffer>>> = flow {
        try {
            emit(Resource.Loading())
            val ticketsOffers = ticketsApi.getTicketsOffers().map { it.toDomain() }
            emit(Resource.Success(ticketsOffers))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
