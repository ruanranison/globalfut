package com.example.globalfut.core.model.post

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey val id: Int,
    val teamLogoUrl: String,
    val teamName: String,
    val userName: String,
    val timestamp: String,
    val postText: String,
    val commentsCount: Int,
    val likesCount: Int
)