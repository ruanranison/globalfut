package com.example.globalfut.core.model.teams

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Teams(
    @PrimaryKey val id: Int,
    val teamName: String,
    val leagueName: String,
    val logoUrl: String,
)
