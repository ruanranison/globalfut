package com.example.globalfut.modules.feature_teams.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.globalfut.modules.feature_teams.data.Team
import com.example.globalfut.modules.feature_teams.repository.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TeamsViewModel(private val repository: TeamRepository) : ViewModel() {

    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> = _teams

    init {
        loadTeams()
    }

    fun loadTeams() {
        viewModelScope.launch {
            try {
                _teams.value = repository.getTeams()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val repository: TeamRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TeamsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TeamsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
