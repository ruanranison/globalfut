package com.example.globalfut.modules.feature_players.data.repository

import android.util.Log
import com.example.globalfut.modules.feature_players.data.local.LocalPlayersDataSource
import com.example.globalfut.core.model.postPlayers.PlayerPost
import com.example.globalfut.modules.feature_players.data.remote.RemotePlayersApiDataSource

class PlayerPostRepository(
    private val localPlayersDataSource: LocalPlayersDataSource,
    private val remotePlayersApiDataSource: RemotePlayersApiDataSource
) {
    suspend fun findAll(): List<PlayerPost> {
        var posts: List<PlayerPost>
        try {
            posts = remotePlayersApiDataSource.getPlayerPosts()
            println("playerPosts: $posts")
            localPlayersDataSource.deleteAll()
            localPlayersDataSource.insertAll(posts)

        } catch (e: Exception) {
            posts = localPlayersDataSource.getAll()
        }
        Log.d("PlayerPostRepository", "playerPosts: $posts")

        return posts
    }
}

