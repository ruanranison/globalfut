package com.example.globalfut.modules.feature_home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.globalfut.core.model.post.Banner
import com.example.globalfut.core.model.post.Post

@Dao
interface LocalHomeDataSource {
    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<Post>

    @Insert
    suspend fun insertAll(posts: List<Post>)

    @Query("DELETE FROM posts")
    suspend fun deleteAll()

    @Insert
    suspend fun upsertAllBanners(banners: List<Banner>)

    @Query("SELECT * FROM banners")
    suspend fun getAllBanners(): List<Banner>
}