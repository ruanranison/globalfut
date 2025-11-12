package com.example.globalfut.core.model.postPlayers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postPlayers")
data class PlayerPost(
    @PrimaryKey val id: Int,
    val playerName: String,
    val playerCity: String,
    val postText: String,
    val postImage: String,
    val timeLabel: String,
    val commentsCount: Int,
    val likesCount: Int
)
