package com.example.globalfut.core.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.globalfut.core.presentation.viewmodels.PlayersViewModel
import com.example.globalfut.core.presentation.viewmodels.PlayersViewModelFactory
import kotlinx.coroutines.launch

import com.example.globalfut.core.data.models.Player
import com.example.globalfut.core.data.models.Post
import com.example.globalfut.core.ui.components.GFHeader
import com.example.globalfut.core.ui.components.GFSearchInput
import com.example.globalfut.core.ui.components.GFPostCard
import com.example.globalfut.core.ui.components.GFPlayerStatsCard


enum class PlayerTab(val title: String) {
    STATS("Estatísticas"),
    POSTS("Publicações")
}
private val PrimaryColor = Color(0xFF00A398)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayersScreen(
    factory: PlayersViewModelFactory,
    navController: NavController
) {
    val viewModel: PlayersViewModel = viewModel(factory = factory)
    val tabs = PlayerTab.entries.toTypedArray()
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { GFHeader() }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            // --- CÓDIGO DE BUSCA CORRIGIDO ---
            var searchText by remember { mutableStateOf("") }

            // Usando o componente existente GFSearchInput:
            GFSearchInput(
                query = searchText,
                onQueryChange = { searchText = it },
                onSearchClick = {
                    // Aqui você implementa a lógica real de filtragem da lista
                    println("Buscando por: $searchText")
                },
                modifier = Modifier.padding(vertical = 8.dp) // Adiciona um espaçamento vertical
            )

            // Estrutura de Abas
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        color = PrimaryColor
                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(text = tab.title, color = if (pagerState.currentPage == index) PrimaryColor else Color.Gray, fontWeight = FontWeight.SemiBold) }
                    )
                }
            }

            // Conteúdo das abas
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                when (tabs[page]) {
                    PlayerTab.STATS -> PlayerStatsView(viewModel)
                    PlayerTab.POSTS -> PlayerPostsView(viewModel)
                }
            }
        }
    }
}

// --- View de Estatísticas (DB - Requisito 2) ---
@Composable
fun PlayerStatsView(viewModel: PlayersViewModel) {
    val statsList by viewModel.playerStats.observeAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(statsList) { player ->
            GFPlayerStatsCard(player = player)
        }
    }
    if (statsList.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
    }
}

// --- View de Publicações (API - Requisito 3) ---
@Composable
fun PlayerPostsView(viewModel: PlayersViewModel) {
    val postsList by viewModel.playerPosts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(postsList) { post ->
                GFPostCard(post = post)
                Divider(color = Color.LightGray)
            }
        }
    }
}