package com.example.globalfut.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.globalfut.core.data.models.Player

private val PrimaryColor = Color(0xFF00A398)

@Composable
fun GFPlayerStatsCard(player: Player) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Filled.Person,
                contentDescription = player.name,
                modifier = Modifier.size(60.dp).clip(CircleShape).background(Color.Red).padding(4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(player.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
            Text(player.clubLocation, fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(label = "Posts:", value = player.posts.toString())
                StatItem(label = "Idade:", value = player.age.toString())
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = { /* Ação de navegação */ },
                modifier = Modifier.fillMaxWidth().height(36.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryColor),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp, color = PrimaryColor)
            ) {
                Text("Ver Detalhes", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Row {
        Text(label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.Black)
        Spacer(modifier = Modifier.width(4.dp))
        Text(value, fontSize = 14.sp, color = Color.Black)
    }
}
// ... (Preview omitido por brevidade, mas deve estar no arquivo)