package com.example.globalfut.modules.feature_home.data.remote
import com.example.globalfut.core.model.post.Banner
import retrofit2.http.GET

interface RemoteHomeApiDataSource {
    @GET("banners/")
    suspend fun getHome(): List<Banner>
}