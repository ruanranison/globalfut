package com.example.globalfut.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.globalfut.modules.feature_players.PublicationsSection
import com.example.globalfut.modules.feature_players.StatisticsSection

// O pacote deve ser o mesmo do BFTabsOptions/**
// ... outros imports
// Importe o novo objeto de constantes que está junto dos seus componentes
// Lembre-se de substituir estas seções pelas seções corretas de 'Times'
@Composable
fun TeamsScreen() {

    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }

    Column {
        // ... (GFHeader, GFSearchInput, etc.)

        Spacer(modifier = Modifier.height(18.dp))

        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            BFTabsOptions(
                // MODIFICAÇÃO AQUI: Usando a constante importada
                options = TabsOptionsConstants.TEAM_TABS,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                onOptionSelected = { index ->
                    selectedTab = index
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Lembre-se que o próximo passo é criar seções específicas para Times
        // e usá-las aqui (ex: LeaguesSection())
        when (selectedTab) {
            0 -> StatisticsSection() // Temporário
            1 -> PublicationsSection() // Temporário
        }
    }
}

object TabsOptionsConstants {

    /**
     * Opções de abas para a tela de Jogadores (PlayerScreen).
     */
    val PLAYER_TABS = listOf("Estatísticas", "Publicações")

    /**
     * Opções de abas para a tela de Times (TeamsScreen).
     */
    val TEAM_TABS = listOf("Ligas", "Publicações")
}
