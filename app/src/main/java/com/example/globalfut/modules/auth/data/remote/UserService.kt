package com.example.globalfut.modules.auth.data.remote

import retrofit2.http.GET

data class UserResponse(
    val username: String,
    val password: String
)

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}