package com.example.globalfut.modules.feature_home.data.remote

import retrofit2.http.GET

data class Banner(
    val id: Int,
    val imageUrl: String
)

data class HomeResponse(
    val banners: List<Banner>
)

interface HomeService {
    @GET("home")
    suspend fun getHome(): HomeResponse
}