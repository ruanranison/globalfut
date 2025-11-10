package com.example.globalfut.core.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.globalfut.core.data.models.Post
import com.example.globalfut.core.domain.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayersViewModel(private val repository: PlayerRepository) : ViewModel() {

    val playerStats = repository.getPlayerStats().asLiveData()

    private val _playerPosts = MutableStateFlow<List<Post>>(emptyList())
    val playerPosts: StateFlow<List<Post>> = _playerPosts

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        populateDbForDemo()
        loadPlayerPosts()
    }

    private fun populateDbForDemo() {
        viewModelScope.launch {
            repository.populateDatabase()
        }
    }

    fun loadPlayerPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val posts = withContext(Dispatchers.IO) {
                    repository.getPlayerPosts()
                }
                _playerPosts.value = posts.take(5)
            } catch (e: Exception) {
                // Simulação de dados da API em caso de falha de rede
                _playerPosts.value = listOf(
                    Post(1, "Felipe Santos", "Mais um treino na benção de Deus.", "url", 52, 237),
                    Post(2, "Bruno Henrique", "Pronto para o jogo!", "url", 99, 150)
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
}