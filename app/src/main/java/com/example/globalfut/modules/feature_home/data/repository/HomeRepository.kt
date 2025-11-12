package com.example.globalfut.modules.feature_home.data.repository;

import com.example.globalfut.modules.feature_home.data.remote.HomeService

class HomeRepository(private val api: HomeService) {

    suspend fun getBanners() = api.getHome().banners
}