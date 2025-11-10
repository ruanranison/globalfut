package com.example.globalfut.core

import android.content.Context
import com.example.globalfut.core.data.api.ApiService
import com.example.globalfut.core.data.db.AppDatabase
import com.example.globalfut.core.domain.PlayerRepository
import com.example.globalfut.core.presentation.viewmodels.PlayersViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppContainer {

    // Mude esta URL para a sua API real/fake!
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private lateinit var database: AppDatabase
    private lateinit var playerRepository: PlayerRepository

    // Inicializa o DB e o Repositório
    fun initialize(context: Context) {
        database = AppDatabase.getDatabase(context)
        playerRepository = PlayerRepository(database.playerDao(), apiService)
    }

    private fun getPlayerRepository(): PlayerRepository {
        if (!this::playerRepository.isInitialized) {
            throw IllegalStateException("AppContainer não inicializado.")
        }
        return playerRepository
    }

    fun getPlayersViewModelFactory(): PlayersViewModelFactory {
        return PlayersViewModelFactory(getPlayerRepository())
    }
}