package com.example.globalfut.modules.feature_home.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globalfut.modules.feature_home.data.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
    data class Success(val banners: List<String>) : HomeState()
    data class Error(val message: String) : HomeState()
}

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Idle)
    val state: StateFlow<HomeState> = _state

    fun loadHome() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            try {
                val banners = repository.getBanners().map { it.imageUrl }
                _state.value = HomeState.Success(banners)
            } catch (e: Exception) {
                _state.value = HomeState.Error("Erro ao carregar banners")
            }
        }
    }
}
