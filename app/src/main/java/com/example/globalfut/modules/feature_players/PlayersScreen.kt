package com.example.globalfut.modules.feature_players

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.globalfut.core.ui.components.BFTabsOptions
import com.example.globalfut.core.ui.components.GFHeader
import com.example.globalfut.core.ui.components.GFSearchInput

@Composable
fun PlayersScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }

    Column {
        GFHeader(
            showSearchBar = true,
            onProfileClick = { /* navega para Perfil */ },
            onNotificationClick = { /* abre notificações */ },
            onSearchClick = { query -> /* filtra jogadores */ }
        )
        Spacer(modifier = Modifier.height(18.dp))


            GFSearchInput(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearchClick = {
                    println("Pesquisando por: $searchQuery")
                }
            )

        Spacer(modifier = Modifier.height(18.dp))

        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
        ){
            BFTabsOptions (
                options = listOf("Estatísticas", "Publicações"),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ,
                onOptionSelected = { index ->
                    selectedTab = index
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (selectedTab) {
            0 -> StatisticsSection()
            1 -> PublicationsSection()
        }

        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("🏠 Tela de Jogadores", style = MaterialTheme.typography.headlineSmall)
        }
    }

}


@Composable
fun StatisticsSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Nível: 12", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("Vitórias: 8", fontSize = 16.sp)
        Text("Derrotas: 3", fontSize = 16.sp)
    }
}

@Composable
fun PublicationsSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📢 Últimas publicações", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("• GlobalFut anuncia torneio regional")
        Text("• Novas estatísticas disponíveis no app")
    }
}

