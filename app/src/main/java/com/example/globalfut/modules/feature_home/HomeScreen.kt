package com.example.globalfut.modules.feature_home

import GFButtonTabs
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.globalfut.core.ui.components.GFHeader
import androidx.compose.ui.unit.dp
import com.example.globalfut.R
import com.example.globalfut.core.ui.components.GFImageCarousel
import com.example.globalfut.core.ui.components.GFSearchInput
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.globalfut.core.ui.components.GFPostCard
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = useDarkIcons
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        GFHeader(
            showSearchBar = true,
            onProfileClick = { /* navega para Perfil */ },
            onNotificationClick = { /* abre notificações */ },
            onSearchClick = { query -> /* filtra jogadores */ }
        )

        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            val images = listOf(
                painterResource(id = R.drawable.image_carousel),
                painterResource(id = R.drawable.image_carousel_2),
                painterResource(id = R.drawable.image_carousel)
            )

            GFImageCarousel(images = images)
            val myTabs = listOf("Em alta", "Peneiras", "Vídeos", "Fotos")

            Spacer(modifier = Modifier.height(18.dp))
            GFButtonTabs(tabs = myTabs) { index ->
                println("Aba selecionada: $index")
            }

            Spacer(modifier = Modifier.height(18.dp))
            GFSearchInput(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearchClick = {
                    println("Pesquisando por: $searchQuery")
                }
            )

            Spacer(modifier = Modifier.height(18.dp))
            GFPostCard(
                teamLogoRes = R.drawable.santos_logo,
                teamName = "Santos",
                userName = "Júnior do canavial",
                timestamp = "Hoje 13:21",
                postText = "ATENÇÃO: Dia 23/12 irá acontecer uma peneira na cidade de Limoeiro, fiquem atentos que continuarei postando novidades...",
                commentsCount = 10,
                likesCount = 32
            )

            GFPostCard(
                teamLogoRes = R.drawable.santos_logo,
                teamName = "Santos",
                userName = "Júnior do canavial",
                timestamp = "Hoje 13:21",
                postText = "ATENÇÃO: Dia 23/12 irá acontecer uma peneira na cidade de Limoeiro, fiquem atentos que continuarei postando novidades...",
                commentsCount = 10,
                likesCount = 32
            )

            GFPostCard(
                teamLogoRes = R.drawable.santos_logo,
                teamName = "Santos",
                userName = "Júnior do canavial",
                timestamp = "Hoje 13:21",
                postText = "ATENÇÃO: Dia 23/12 irá acontecer uma peneira na cidade de Limoeiro, fiquem atentos que continuarei postando novidades...",
                commentsCount = 10,
                likesCount = 32
            )

            GFPostCard(
                teamLogoRes = R.drawable.santos_logo,
                teamName = "Santos",
                userName = "Júnior do canavial",
                timestamp = "Hoje 13:21",
                postText = "ATENÇÃO: Dia 23/12 irá acontecer uma peneira na cidade de Limoeiro, fiquem atentos que continuarei postando novidades...",
                commentsCount = 10,
                likesCount = 32
            )
        }
    }
}
