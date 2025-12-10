package com.example.globalfut.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.globalfut.core.model.postPlayers.PlayerPost

@Dao
interface LocalPlayersDataSource {
    @Query("SELECT * FROM postPlayers")
    suspend fun getAll(): List<PlayerPost>

    @Insert
    suspend fun insertAll(posts: List<PlayerPost>)

    @Query("DELETE FROM postPlayers")
    suspend fun deleteAll()
}

