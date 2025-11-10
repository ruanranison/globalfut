package com.example.globalfut.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_stats")
data class Player(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val clubLocation: String,
    val posts: Int,
    val age: Int,
    val imageUrl: String
)

data class Post(
    val postId: Int,
    val playerName: String,
    val postContent: String,
    val imageUrl: String,
    val likes: Int,
    val comments: Int
)