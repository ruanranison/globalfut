package com.example.globalfut.modules.feature_home.data.repository;

import android.util.Log
import com.example.globalfut.core.db.LocalHomeDataSource
import com.example.globalfut.core.model.post.Banner
import com.example.globalfut.modules.feature_home.data.remote.RemoteHomeApiDataSource

class HomeRepository(
    private val localHomeDataSource: LocalHomeDataSource,
    private val remoteHomeApiDataSource: RemoteHomeApiDataSource
) {
    suspend fun findAll(): List<Banner> {
        var banners: List<Banner>
        try {
            banners = remoteHomeApiDataSource.getHome()
            println("banners: $banners")
            localHomeDataSource.upsertAllBanners(banners)

        } catch (e: Exception) {
            banners = localHomeDataSource.getAllBanners()
        }
        Log.d("HomeRepository", "banners: $banners")

        return banners
    }
}