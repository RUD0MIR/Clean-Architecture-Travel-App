package com.rud.domain.repository

import com.rud.domain.model.Offer
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface OffersRepository {
    suspend fun getOffers() : Response<ArrayList<Offer>>
}