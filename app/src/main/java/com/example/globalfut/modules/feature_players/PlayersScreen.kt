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
import com.example.globalfut.modules.feature_players.ui.components.PlayerCard
import com.example.globalfut.modules.feature_players.ui.components.PlayerPostCard
import com.example.globalfut.R
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
    val players = listOf(
        Triple("Gabigol", "Limoeiro - AL", Pair(23, 16)),
        Triple("Pedro", "Maceió - AL", Pair(50, 18)),
        Triple("Everton", "Arapiraca - AL", Pair(30, 21))
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlowRow(
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.Center
        ) {
            players.forEach { (name, city, stats) ->
                PlayerCard(
                    name = name,
                    city = city,
                    posts = stats.first,
                    age = stats.second,
                    onDetailsClick = { println("Clicou em $name") }
                )
            }
        }
    }
}


@Composable
fun PublicationsSection() {
    val posts = listOf(
        Triple("Felipe Santos", "Limoeiro - AL", "Mais um treino na benção de Deus."),
        Triple("Bruno Henrique", "São Paulo - SP", "Treino intenso hoje! ⚽🔥"),
        Triple("Pedro Silva", "Maceió - AL", "Treino finalizado com sucesso! 💪"),
        Triple("Gabigol", "Limoeiro - AL", "Preparando para o próximo jogo ⚽"),
        Triple("João Victor", "Recife - PE", "Trabalhando firme nos treinos 💥")
    )

    androidx.compose.foundation.lazy.LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(posts.size) { index ->
            val (name, city, text) = posts[index]
            PlayerPostCard(
                playerName = name,
                playerCity = city,
                postImage = com.example.globalfut.R.drawable.post_default,
                postText = text,
                timeLabel = "12:32",
                comments = (10..80).random(),
                likes = (100..300).random()
            )
        }
    }
}


