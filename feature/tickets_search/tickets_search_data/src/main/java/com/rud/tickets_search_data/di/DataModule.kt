package com.rud.tickets_search_data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.rud.common.Constants
import com.rud.tickets_search_domain.repository.DataStoreRepository
import com.rud.tickets_search_data.local.DataStoreRepositoryImpl
import com.rud.tickets_search_data.remote.TicketsApi
import com.rud.tickets_search_data.remote.repository.FAKETicketsOffersRepositoryImpl
import com.rud.tickets_search_data.remote.repository.FAKETicketsRecommendationsRepositoryImpl
import com.rud.tickets_search_data.remote.repository.TicketsOffersRepositoryImpl
import com.rud.tickets_search_data.remote.repository.TicketsRecommendationsRepositoryImpl
import com.rud.tickets_search_domain.repository.TicketsOffersRepository
import com.rud.tickets_search_domain.repository.TicketsRecommendationsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(): TicketsApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TicketsApi::class.java)

    @Provides
    @Singleton
    fun provideTicketsOffersRepository(ticketsApi: TicketsApi): TicketsOffersRepository {
        //TODO: use real implementation: return TicketsOffersRepositoryImpl(ticketsApi)
        return FAKETicketsOffersRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideTicketsRecommendationsRepository(ticketsApi: TicketsApi): TicketsRecommendationsRepository {
        //TODO: use real implementation: return TicketsRecommendationsRepositoryImpl(ticketsApi)
        return FAKETicketsRecommendationsRepositoryImpl()
    }

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile("data_store")
            }
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DataStoreRepository {
        return DataStoreRepositoryImpl(dataStore)
    }
}