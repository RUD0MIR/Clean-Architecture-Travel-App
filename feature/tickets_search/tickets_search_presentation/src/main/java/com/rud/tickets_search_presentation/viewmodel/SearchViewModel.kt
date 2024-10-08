package com.rud.tickets_search_presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rud.common.Resource
import com.rud.tickets_search_domain.model.TicketOffer
import com.rud.tickets_search_domain.model.TicketRecommendation
import com.rud.tickets_search_domain.usecase.GetTicketsRecommendationsUseCase
import com.rud.tickets_search_domain.usecase.GetTicketsOffersUseCase
import com.rud.tickets_search_domain.usecase.LoadDepartureUseCase
import com.rud.tickets_search_domain.usecase.SaveDepartureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class SearchViewModel @Inject constructor(
    val getTicketsOffersUseCase: GetTicketsOffersUseCase,
    loadDepartureUseCase: LoadDepartureUseCase,
    val saveDepartureUseCase: SaveDepartureUseCase,
    val getTicketsRecommendationsUseCase: GetTicketsRecommendationsUseCase
) : ViewModel() {
    private val _ticketsOffers = MutableLiveData<List<TicketOffer>>()
    val ticketsOffers: LiveData<List<TicketOffer>>
        get() = _ticketsOffers

    private val _ticketsRecommendations = MutableLiveData<List<TicketRecommendation>>()
    val ticketsRecommendations: LiveData<List<TicketRecommendation>>
        get() = _ticketsRecommendations

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val departure: StateFlow<String> = loadDepartureUseCase().stateIn(
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
        fetchTicketsOffers()
        fetchTicketsRecommendations()
    }

    private fun fetchTicketsOffers() {
        getTicketsOffersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _ticketsOffers.postValue(result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _error.postValue(result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _isLoading.postValue(true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun fetchTicketsRecommendations() {
        getTicketsRecommendationsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _ticketsRecommendations.postValue(result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _error.postValue(result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _isLoading.postValue(true)
                }
            }
        }.launchIn(viewModelScope)
    }
}