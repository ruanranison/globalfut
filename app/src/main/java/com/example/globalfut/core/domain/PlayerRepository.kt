package com.example.globalfut.core.domain

import com.example.globalfut.core.data.api.ApiService
import com.example.globalfut.core.data.db.PlayerDao
import com.example.globalfut.core.data.models.Player
import com.example.globalfut.core.data.models.Post
import kotlinx.coroutines.flow.Flow

class PlayerRepository(
    private val playerDao: PlayerDao,
    private val apiService: ApiService
) {
    fun getPlayerStats(): Flow<List<Player>> {
        return playerDao.getAllPlayerStats()
    }

    suspend fun getPlayerPosts(): List<Post> {
        return apiService.getPlayerPosts()
    }

    suspend fun populateDatabase() {
        // Simulação de dados para a sua tela de Estatísticas
        val dummyPlayers = listOf(
            Player(1, "Gabigol", "Limoeiro - AL", 23, 16, "url_foto_1"),
            Player(2, "Outro Jogador", "Limoeiro - AL", 50, 18, "url_foto_2"),
            Player(3, "Gabigol", "Limoeiro - AL", 23, 16, "url_foto_3"),
            Player(4, "Outro Jogador", "Limoeiro - AL", 50, 18, "url_foto_4")
        )
        dummyPlayers.forEach { playerDao.insertPlayer(it) }
    }
}