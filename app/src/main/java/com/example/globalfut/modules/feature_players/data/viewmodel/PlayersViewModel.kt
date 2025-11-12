package com.example.globalfut.modules.feature_players.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globalfut.modules.feature_players.data.repository.PlayerPostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class PlayerPostState {
    object Idle : PlayerPostState()
    object Loading : PlayerPostState()
    data class Success(val posts: List<PlayerPostUiModel>) : PlayerPostState()
    data class Error(val message: String) : PlayerPostState()
}

data class PlayerPostUiModel(
    val playerName: String,
    val playerCity: String,
    val postText: String,
    val postImage: String,
    val timeLabel: String,
    val commentsCount: Int,
    val likesCount: Int
)

class PlayerPostViewModel(private val repository: PlayerPostRepository) : ViewModel() {

    private val _state = MutableStateFlow<PlayerPostState>(PlayerPostState.Idle)
    val state: StateFlow<PlayerPostState> = _state

    fun loadPlayerPosts() {
        viewModelScope.launch {
            _state.value = PlayerPostState.Loading
            try {
                val posts = repository.getPlayerPosts().map {
                    PlayerPostUiModel(
                        playerName = it.playerName,
                        playerCity = it.playerCity,
                        postText = it.postText,
                        postImage = it.postImage,
                        timeLabel = it.timeLabel,
                        commentsCount = it.commentsCount,
                        likesCount = it.likesCount
                    )
                }

                _state.value = PlayerPostState.Success(posts)
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = PlayerPostState.Error("Erro ao carregar publicações")
            }
        }
    }
}
