package com.example.globalfut.core.model.post

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banners")
data class Banner(
    @PrimaryKey val id: Int,
    val imageUrl: String
)
