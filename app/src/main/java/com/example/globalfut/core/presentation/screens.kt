// com.example.globalfut.core.presentation.screens/PlayersScreen.kt

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

// Importações AJUSTADAS para os componentes existentes (GFHeader, GFSearchInput, etc.)
import com.example.globalfut.core.data.models.Player
import com.example.globalfut.core.data.models.Post
import com.example.globalfut.core.ui.components.GFHeader // Componente do colega para o topo
import com.example.globalfut.core.ui.components.GFSearchInput // Componente do colega para a busca
import com.example.globalfut.core.ui.components.GFTopTabs // Componente do colega para as abas
import com.example.globalfut.core.ui.components.GFPostCard // Componente do colega para o Card de Post
import com.example.globalfut.core.ui.components.PlayerStatsCard // Componente NOVO (que você criará)


// --- Definições de Navegação e Cores ---

enum class PlayerTab(val title: String) {
    STATS("Estatísticas"),
    POSTS("Publicações")
}

val PrimaryColor = Color(0xFF00A398)

// --- Composable Principal ---

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayersScreen(
    factory: PlayersViewModelFactory,
    navController: NavController
) {
    // 1. Conexão com o ViewModel usando a Factory
    val viewModel: PlayersViewModel = viewModel(factory = factory)

    val tabs = PlayerTab.entries.toTypedArray()
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()

    Scaffold(
        // Substituindo PlayerTopBar pelo GFHeader (Componente existente)
        topBar = { GFHeader() }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            // Substituindo SearchBarComponent pelo GFSearchInput (Componente existente)
            // Assumindo um estado local para a busca, mas você pode usar o ViewModel
            var searchText by remember { mutableStateOf("") }
            GFSearchInput(
                value = searchText,
                onValueChange = { searchText = it },
                // Você pode precisar adicionar um argumento de placeholder/label no GFSearchInput
            )

            // 2. Abas de navegação (Estatísticas / Publicações)
            // Usando GFTopTabs. Se ele não suportar o HorizontalPager,
            // você precisará manter a TabRow original abaixo:
            // --- INÍCIO: Estrutura padrão de Abas do Compose (se GFTopTabs não funcionar) ---
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
                        text = {
                            Text(
                                text = tab.title,
                                color = if (pagerState.currentPage == index) PrimaryColor else Color.Gray,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    )
                }
            }
            // --- FIM: Estrutura padrão de Abas do Compose ---


            // 3. Conteúdo das abas
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
    // Observa o LiveData vindo do Room (DB)
    val statsList by viewModel.playerStats.observeAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(statsList) { player ->
            // Usando o componente NOVO PlayerStatsCard
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
    // Observa o StateFlow vindo da API
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
                // Usando o componente existente GFPostCard
                GFPostCard(post = post) // Adapte a chamada dependendo dos argumentos que GFPostCard espera!
                Divider(color = Color.LightGray)
            }
        }
    }
}