package com.rud.tickets_search_data.local

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.rud.tickets_search_domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val TAG = "DataStoreRepositoryImpl"


class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {
    private object PreferencesKeys {
        val DEPARTURE = stringPreferencesKey("departure")
    }

    override suspend fun saveDeparture(departure: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DEPARTURE] = departure
        }
    }

    override suspend fun loadDeparture(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e(TAG, "Error reading preferences.", exception)
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                Log.d(TAG, preferences[PreferencesKeys.DEPARTURE].toString())
                preferences[PreferencesKeys.DEPARTURE] ?: ""
            }
    }
}

