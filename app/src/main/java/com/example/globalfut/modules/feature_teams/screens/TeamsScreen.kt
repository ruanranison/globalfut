package com.example.globalfut.modules.feature_teams.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.globalfut.R
import com.example.globalfut.core.db.DatabaseHelper
import com.example.globalfut.core.model.teams.Teams
import com.example.globalfut.core.model.postPlayers.playerPostListMock
import com.example.globalfut.core.model.teams.teamListMock
import com.example.globalfut.core.ui.components.BFTabsOptions
import com.example.globalfut.core.ui.components.GFHeader
import com.example.globalfut.core.ui.components.GFSearchInput
import com.example.globalfut.core.ui.components.TeamCard
import com.example.globalfut.modules.feature_players.ui.components.PlayerCard
import com.example.globalfut.modules.feature_players.ui.components.PlayerPostCard

@Composable
fun TeamsScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        GFHeader(
            showSearchBar = true,
            onProfileClick = { /* navega para Perfil */ },
            onNotificationClick = { /* abre notificações */ },
            onSearchClick = { query -> /* busca */ }
        )

        Spacer(modifier = Modifier.height(18.dp))

        GFSearchInput(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearchClick = { println("Pesquisando por: $searchQuery") }
        )

        Spacer(modifier = Modifier.height(18.dp))

        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            BFTabsOptions(
                options = listOf("Ligas", "Times"),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                onOptionSelected = { index -> selectedTab = index }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (selectedTab) {
            0 -> LeaguesSection()
            1 -> TeamsSection()
        }
    }
}

@Composable
fun LeaguesSection() {
    val players = listOf(
        Triple("Gabigol", "Limoeiro - AL", Pair(23, 16)),
        Triple("Pedro", "Maceió - AL", Pair(50, 18)),
        Triple("Everton", "Arapiraca - AL", Pair(30, 21))
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlowRow(
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.Center
        ) {
            players.forEach { (name, city, stats) ->

            }
        }
    }
}

@Composable
fun TeamsSection() {
    val context = LocalContext.current
    val dao = DatabaseHelper.getInstance(context).teamsDao()
    var teams by remember { mutableStateOf(emptyList<Teams>()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            dao.deleteAll()
            dao.insertAll(teamListMock)
            teams = dao.getAll()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        }

        teams.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nenhuma publicação encontrada",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(teams.size) { index ->
                    val post = teams[index]
                    TeamCard (
                        name = post.teamName,
                        city = post.leagueName,
                        posts = 1000,
                        age = 1910,
                        photoUrl = post.logoUrl,
                        onDetailsClick = {}
                    )
                }
            }
        }
    }
}
