package com.example.globalfut.core.network

import com.example.globalfut.modules.auth.data.remote.UserService
import com.example.globalfut.modules.feature_home.data.remote.RemoteHomeApiDataSource
import com.example.globalfut.modules.feature_players.data.remote.RemotePlayersApiDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://my-json-server.typicode.com/ruanranison/globalfut/"
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val homeApi: RemoteHomeApiDataSource = com.example.globalfut.core.network.RetrofitInstance.retrofit.create(RemoteHomeApiDataSource::class.java)




    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val userService: UserService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    val homeApi: RemoteHomeApiDataSource by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteHomeApiDataSource::class.java)
    }

    val playersApi: RemotePlayersApiDataSource by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemotePlayersApiDataSource::class.java)
    }
}

