package com.example.globalfut.modules.feature_players.data.remote

import com.example.globalfut.core.model.postPlayers.PlayerPost
import retrofit2.http.GET

interface RemotePlayersApiDataSource {
    @GET("player_posts")
    suspend fun getPlayerPosts(): List<PlayerPost>
}

