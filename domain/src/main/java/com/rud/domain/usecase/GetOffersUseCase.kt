package com.rud.domain.usecase

import com.rud.domain.model.Offer
import com.rud.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GetOffersUseCase(private val offersRepository: OffersRepository) {
    suspend operator fun invoke() = offersRepository.getOffers()
}