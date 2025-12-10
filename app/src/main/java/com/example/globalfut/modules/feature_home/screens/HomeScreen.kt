package com.example.globalfut.modules.feature_home.screens

import GFButtonTabs
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.globalfut.core.ui.components.GFHeader
import androidx.compose.ui.unit.dp
import com.example.globalfut.core.ui.components.GFImageCarousel
import com.example.globalfut.core.ui.components.GFSearchInput
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.globalfut.core.network.RetrofitInstance
import com.example.globalfut.core.ui.components.GFPostCard
import com.example.globalfut.modules.feature_home.data.repository.HomeRepository
import com.example.globalfut.modules.feature_home.data.viewmodel.HomeState
import com.example.globalfut.modules.feature_home.data.viewmodel.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.globalfut.core.db.DatabaseHelper
import com.example.globalfut.core.model.post.Post
import com.example.globalfut.core.model.post.postListMock

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true

    val state by viewModel.state.collectAsState()

    val dao = DatabaseHelper.getInstance(context = LocalContext.current).postDao()
    var posts by remember { mutableStateOf(emptyList<Post>()) }

    LaunchedEffect(Unit) {
        viewModel.loadHome()
        dao.deleteAll()
        dao.insertAll(postListMock)
        posts = dao.getAllPosts()
    }

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
            when (state) {
                is HomeState.Loading -> {
                    GFImageCarousel(
                        images = listOf()
                    )
                }

                is HomeState.Error -> {
                    Text((state as HomeState.Error).message)
                }

                is HomeState.Success -> {
                    val banners = (state as HomeState.Success).banners

                    GFImageCarousel(
                        images = banners.map { imageUrl ->
                            rememberAsyncImagePainter(model = imageUrl)
                        }
                    )
                }

                else -> {}
            }

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
            if (posts.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhum post disponível",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            } else {
                posts.forEach { post ->
                    GFPostCard(
                        teamLogoUrl = post.teamLogoUrl,
                        teamName = post.teamName,
                        userName = post.userName,
                        timestamp = post.timestamp,
                        postText = post.postText,
                        commentsCount = post.commentsCount,
                        likesCount = post.likesCount
                    )
                }
            }

        }
    }
}
