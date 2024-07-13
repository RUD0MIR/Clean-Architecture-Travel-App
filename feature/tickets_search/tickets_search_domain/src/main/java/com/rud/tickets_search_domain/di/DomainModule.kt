package com.rud.tickets_search_domain.di


import com.rud.tickets_search_domain.repository.DataStoreRepository
import com.rud.tickets_search_domain.repository.TicketsOffersRepository
import com.rud.tickets_search_domain.usecase.GetTicketsOffersUseCase
import com.rud.tickets_search_domain.usecase.LoadDepartureUseCase
import com.rud.tickets_search_domain.usecase.SaveDepartureUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
}