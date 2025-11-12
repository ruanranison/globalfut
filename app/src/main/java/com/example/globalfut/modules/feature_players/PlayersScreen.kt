package com.example.globalfut.modules.feature_players

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
import com.example.globalfut.core.model.postPlayers.PlayerPost
import com.example.globalfut.core.model.postPlayers.playerPostListMock
import com.example.globalfut.core.ui.components.BFTabsOptions
import com.example.globalfut.core.ui.components.GFHeader
import com.example.globalfut.core.ui.components.GFSearchInput
import com.example.globalfut.modules.feature_players.ui.components.PlayerCard
import com.example.globalfut.modules.feature_players.ui.components.PlayerPostCard

@Composable
fun PlayersScreen() {
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
                options = listOf("Estatísticas", "Publicações"),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                onOptionSelected = { index -> selectedTab = index }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (selectedTab) {
            0 -> StatisticsSection()
            1 -> PublicationsSection()
        }
    }
}



@Composable
fun StatisticsSection() {
    val players = listOf(
        Triple("Gabigol", "Limoeiro - AL", Pair(23, 16)) to
                "https://ds-images.bolavip.com/news/image/800/800/?src=https://images.bolavip.com/webp/br/full/BBR_20230813_BBR_96120_AGIF23080123174098-scaled-e1691968719739.webp",

        Triple("Pedro", "Maceió - AL", Pair(50, 18)) to
                "https://www.historiadealagoas.com.br/_next/image?url=https%3A%2F%2Fwp.historiadealagoas.com.br%2Fwp-content%2Fuploads%2F2015%2F07%2FRanilson-Fran%C3%A7a-e1555453341658.jpg&w=1920&q=75",

        Triple("Everton", "Arapiraca - AL", Pair(30, 21)) to
                "https://assets.goal.com/images/v3/blt57080a255185c54c/ruan.jpg?width=1400&upscale=true",
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlowRow(
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.Center
        ) {
            players.forEach { (playerInfo, imageUrl) ->
                val (name, city, stats) = playerInfo

                PlayerCard(
                    name = name,
                    city = city,
                    posts = stats.first,
                    age = stats.second,
                    imageUrl = imageUrl,
                    onDetailsClick = { println("Clicou em $name") }
                )
            }
        }
    }
}


@Composable
fun PublicationsSection() {
    val context = LocalContext.current
    val dao = DatabaseHelper.getInstance(context).playerPostDao()
    var posts by remember { mutableStateOf(emptyList<PlayerPost>()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            dao.deleteAll()
            dao.insertAll(playerPostListMock)
            posts = dao.getAll()
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

        posts.isEmpty() -> {
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
                items(posts.size) { index ->
                    val post = posts[index]
                    PlayerPostCard(
                        playerName = post.playerName,
                        playerCity = post.playerCity,
                        postImage = R.drawable.post_default,
                        postText = post.postText,
                        timeLabel = post.timeLabel,
                        comments = post.commentsCount,
                        likes = post.likesCount
                    )
                }
            }
        }
    }
}
