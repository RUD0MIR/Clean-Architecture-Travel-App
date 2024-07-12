package com.rud.airlinetickets.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rud.domain.model.Offer
import com.rud.domain.usecase.GetOffersUseCase
import com.rud.domain.usecase.LoadDepartureUseCase
import com.rud.domain.usecase.SaveDepartureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    val getOffersUseCase: GetOffersUseCase,
    val loadDepartureUseCase: LoadDepartureUseCase,
    val saveDepartureUseCase: SaveDepartureUseCase
) : ViewModel() {
    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>>
        get() = _offers

    val savedDeparture: StateFlow<String> = flow {
       emitAll(loadDepartureUseCase())
    }.stateIn(
        initialValue = "",
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )

    fun saveDeparture(departure: String) {
        viewModelScope.launch {
            saveDepartureUseCase(departure)
        }
    }

    init {
        fetchOffers()
    }

    private fun fetchOffers() {
        viewModelScope.launch {
            getOffersUseCase().let { response ->
                if (response.isSuccessful){
                    _offers.postValue(response.body())
                }else{
                    Log.d(TAG, "Response is unsuccessful, error code: ${response.code()}")
                }
            }
        }
    }
}