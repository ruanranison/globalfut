package com.example.globalfut.modules.feature_teams

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.globalfut.R
import com.example.globalfut.core.ui.components.*
import androidx.compose.ui.res.painterResource


@Composable
fun TeamsScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }

    Column {
        GFHeader(
            showSearchBar = true,
            onProfileClick = {},
            onNotificationClick = {},
            onSearchClick = {}
        )

        Spacer(modifier = Modifier.height(18.dp))

        GFSearchInput(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearchClick = { println("Pesquisando por TIME: $searchQuery") }
        )

        Spacer(modifier = Modifier.height(18.dp))

        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            BFTabsOptions(
                options = TabsOptionsConstants.TEAM_TABS,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                onOptionSelected = { index -> selectedTab = index }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (selectedTab) {
            0 -> LeaguesSection()
            1 -> TeamPublicationsScreen()
        }
    }
}

// -----------------------------
// LEAGUES
// -----------------------------

data class League(
    val name: String,
    val series: String,
    val logoUrl: String
)

val sampleLeagues = listOf(
    League(
        name = "Brasileirão",
        series = "Série A",
        logoUrl = "https://logodetimes.com/times/brasil/campeonato-brasileiro-serie-a.png"
    ),
    League(
        name = "Premier League",
        series = "Inglaterra",
        logoUrl = "https://logodetimes.com/times/inglaterra/premier-league.png"
    ),
    League(
        name = "La Liga",
        series = "Espanha",
        logoUrl = "https://logodetimes.com/times/espanha/la-liga.png"
    )
)

@Composable
fun LeaguesSection() {
    LazyColumn {
        items(sampleLeagues) { league ->
            LeagueCard(league = league, onDetailsClick = { println("Detalhes de ${league.name}") })
        }
    }
}

// -----------------------------
// PUBLICAÇÕES DOS TIMES
// -----------------------------

@Composable
fun TeamPublicationsScreen() {
    val teams = listOf(
        Triple("Flamengo", "Rio de Janeiro - RJ", "Treino intenso antes do clássico! 🔥⚽"),
        Triple("Palmeiras", "São Paulo - SP", "Trabalhando forte rumo a mais uma vitória! 💪💚"),
        Triple("Corinthians", "São Paulo - SP", "Preparação total para o próximo jogo! 🏟️"),
        Triple("Grêmio", "Porto Alegre - RS", "Dia de treino tático com o grupo completo! 💙🤍"),
        Triple("Bahia", "Salvador - BA", "Energia boa nos treinos de hoje! 🔵⚪🔴"),
        Triple("Atlético Mineiro", "Belo Horizonte - MG", "Trabalho firme e foco total no objetivo! ⚫⚪")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(teams.size) { index ->
            val (teamName, teamCity, postText) = teams[index]
            TeamPostCard(
                teamName = teamName,
                teamCity = teamCity,
                logoUrl = getTeamLogo(teamName),
                postText = postText,
                timeLabel = "12:${10 + index}",
                comments = (5..50).random(),
                likes = (80..400).random()
            )
        }
    }
}

@Composable
fun TeamPostCard(
    teamName: String,
    teamCity: String,
    logoUrl: String,
    postText: String,
    timeLabel: String,
    comments: Int,
    likes: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .shadow(2.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            // Cabeçalho
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = logoUrl,
                    contentDescription = "$teamName logo",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(teamName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(teamCity, fontSize = 13.sp, color = Color.Gray)
                }

                Text(timeLabel, fontSize = 12.sp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(postText, fontSize = 15.sp, modifier = Modifier.padding(bottom = 8.dp))

            Image(
                painter = painterResource(id = R.drawable.post_default),
                contentDescription = "Post image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Favorite, contentDescription = "Likes", tint = Color.Red, modifier = Modifier.size(20.dp))
                    Text("$likes curtidas", fontSize = 14.sp, modifier = Modifier.padding(start = 4.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Comment, contentDescription = "Comentários", tint = Color.Gray, modifier = Modifier.size(20.dp))
                    Text("$comments comentários", fontSize = 14.sp, modifier = Modifier.padding(start = 4.dp))
                }
            }
        }
    }
}

// -----------------------------
// FUNÇÃO AUXILIAR
// -----------------------------

fun getTeamLogo(team: String): String {
    return when (team) {
        "Flamengo" -> "https://upload.wikimedia.org/wikipedia/pt/2/2e/Flamengo_braz_logo.png"
        "Palmeiras" -> "https://upload.wikimedia.org/wikipedia/commons/1/10/Palmeiras_logo.png"
        "Corinthians" -> "https://upload.wikimedia.org/wikipedia/commons/5/5c/Corinthians_logo.png"
        "Grêmio" -> "https://upload.wikimedia.org/wikipedia/commons/5/5a/Gr%C3%AAmio_Foot-Ball_Porto_Alegrense_crest.svg.png"
        "Bahia" -> "https://upload.wikimedia.org/wikipedia/commons/2/2e/EC_Bahia_logo.png"
        "Atlético Mineiro" -> "https://upload.wikimedia.org/wikipedia/commons/1/1b/Clube_Atl%C3%A9tico_Mineiro_logo.png"
        else -> "https://upload.wikimedia.org/wikipedia/commons/7/7a/Brazilian_Football_Confederation_logo.png"
    }
}
