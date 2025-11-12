package com.example.globalfut.modules.feature_players.data.repository

import com.example.globalfut.modules.feature_players.data.remote.PlayerPostService

class PlayerPostRepository(private val api: PlayerPostService) {

    suspend fun getPlayerPosts() = api.getPlayerPosts()
}

