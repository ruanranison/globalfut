    package com.example.globalfut.modules.feature_players.data.remote

    import retrofit2.http.GET

    data class PlayerPostResponse(
        val id: Int,
        val playerName: String,
        val playerCity: String,
        val postText: String,
        val postImage: String,
        val timeLabel: String,
        val commentsCount: Int,
        val likesCount: Int
    )

    interface PlayerPostService {
        @GET("player_posts")
        suspend fun getPlayerPosts(): List<PlayerPostResponse>
    }
