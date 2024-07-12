package com.rud.data.remote.repository

import com.rud.data.remote.AirlineApi
import com.rud.domain.repository.OffersRepository
import javax.inject.Inject

private const val TAG = "OffersRepositoryImpl"

class OffersRepositoryImpl @Inject constructor(private val airlineApi: AirlineApi) :
    OffersRepository {
    override suspend fun getOffers() = airlineApi.getOffers()

}
