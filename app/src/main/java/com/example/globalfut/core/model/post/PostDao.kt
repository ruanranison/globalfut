package com.example.globalfut.core.model.post

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<Post>

    @Insert
    suspend fun insertAll(properties: List<Post>)

    @Query("DELETE FROM posts")
    suspend fun deleteAll()
}
