package com.example.globalfut.core.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.globalfut.core.domain.PlayerRepository

class PlayersViewModelFactory(
    private val repository: PlayerRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayersViewModel::class.java)) {
            return PlayersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}