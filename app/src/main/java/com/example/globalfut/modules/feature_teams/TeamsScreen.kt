package com.example.globalfut.modules.feature_teams.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.globalfut.core.network.RetrofitInstance
import com.example.globalfut.modules.feature_teams.data.Team
import com.example.globalfut.modules.feature_teams.repository.TeamRepository
import com.example.globalfut.modules.feature_teams.viewmodel.TeamsViewModel

@Composable
fun TeamsScreen() {
    var selectedTab by remember { mutableStateOf(0) } // 0=Ligas, 1=Publicações

    val repository = remember { TeamRepository(RetrofitInstance.teamService) }
    val viewModel: TeamsViewModel = viewModel(factory = TeamsViewModel.Factory(repository))
    val teams by viewModel.teams.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Ligas", "Publicações").forEachIndexed { index, title ->
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = if (selectedTab == index) Color.Black else Color.Gray,
                    modifier = Modifier.clickable { selectedTab = index }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        when (selectedTab) {
            0 -> LeaguesTab(teams)
            1 -> PostsTab(teams)
        }
    }
}

@Composable
fun LeaguesTab(teams: List<Team>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(teams) { team ->
            LeagueCard(team.name, team.city, team.logoUrl)
        }
    }
}

@Composable
fun PostsTab(teams: List<Team>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(teams) { team ->
            PostCard(team.name, team.city, team.logoUrl, team.postText)
        }
    }
}

@Composable
fun LeagueCard(name: String, city: String, logoUrl: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(logoUrl),
            contentDescription = name,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(name, fontSize = 16.sp, color = Color.Black)
            Text(city, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun PostCard(teamName: String, city: String, logoUrl: String, postText: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(logoUrl),
                contentDescription = teamName,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(teamName, fontSize = 16.sp, color = Color.Black)
                Text(city, fontSize = 14.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(postText, fontSize = 15.sp, color = Color.DarkGray)
    }
}
