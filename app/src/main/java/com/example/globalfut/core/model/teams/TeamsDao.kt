package com.example.globalfut.core.model.teams

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.globalfut.core.model.teams.Teams

@Dao
interface TeamsDao {

    @Query("SELECT * FROM teams")
    suspend fun getAll(): List<Teams>

    @Insert
    suspend fun insertAll(posts: List<Teams>)

    @Query("DELETE FROM teams")
    suspend fun deleteAll()
}
