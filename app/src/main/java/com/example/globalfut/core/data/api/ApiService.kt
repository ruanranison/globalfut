package com.example.globalfut.core.data.api

import com.example.globalfut.core.data.models.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPlayerPosts(): List<Post>
}