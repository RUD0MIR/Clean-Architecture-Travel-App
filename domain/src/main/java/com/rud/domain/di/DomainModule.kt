package com.rud.domain.di


import com.rud.domain.repository.DataStoreRepository
import com.rud.domain.repository.OffersRepository
import com.rud.domain.usecase.GetOffersUseCase
import com.rud.domain.usecase.LoadDepartureUseCase
import com.rud.domain.usecase.SaveDepartureUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun provideGetOffersUseCase(offersRepository: OffersRepository): GetOffersUseCase {
        return GetOffersUseCase(offersRepository)
    }

    @Provides
    fun provideLoadDepartureUseCase(dataStoreRepository: DataStoreRepository): LoadDepartureUseCase {
        return LoadDepartureUseCase(dataStoreRepository)
    }

    @Provides
    fun provedSaveDepartureUseCase(dataStoreRepository: DataStoreRepository) : SaveDepartureUseCase {
        return SaveDepartureUseCase(dataStoreRepository)
    }
}