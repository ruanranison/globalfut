package com.example.globalfut.modules.feature_players.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.globalfut.R
import com.example.globalfut.core.ui.theme.GFPrimary

@Composable
fun PlayerCard(
    name: String,
    city: String,
    posts: Int,
    age: Int,
    onDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(180.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🖼️ Imagem e informações lado a lado
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Foto
                Image(
                    painter = painterResource(id = R.drawable.gabigol),
                    contentDescription = "Foto do jogador",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(10.dp))

                // Informações do jogador
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E3A59)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = null,
                            tint = Color(0xFF2E3A59),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = city,
                            fontSize = 13.sp,
                            color = Color(0xFF2E3A59)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Posts: $posts",
                        fontSize = 14.sp,
                        color = Color(0xFF2E3A59),
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Idade: $age",
                        fontSize = 14.sp,
                        color = Color(0xFF2E3A59),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔘 Botão “Ver Detalhes”
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .border(
                        BorderStroke(1.dp, GFPrimary),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onDetailsClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ver Detalhes",
                    fontSize = 15.sp,
                    color = GFPrimary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
