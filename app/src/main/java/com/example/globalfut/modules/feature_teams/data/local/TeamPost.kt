package com.example.globalfut.modules.feature_teams.data.local

data class TeamPost(
    val teamName: String,
    val teamLogoUrl: String,
    val userName: String,
    val timestamp: String,
    val postText: String,
    val commentsCount: Int,
    val likesCount: Int
)
