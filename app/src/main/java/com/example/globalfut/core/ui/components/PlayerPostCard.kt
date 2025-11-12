package com.example.globalfut.modules.feature_players.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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

@Composable
fun PlayerPostCard(
    playerName: String,
    playerCity: String,
    postImage: Int,
    postText: String,
    timeLabel: String,
    comments: Int,
    likes: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            // 🧍 Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.gabigol), // foto do jogador
                        contentDescription = "Foto do jogador",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(50))
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(
                            text = playerName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E3A59)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Place,
                                contentDescription = null,
                                tint = Color(0xFF2E3A59),
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = playerCity,
                                fontSize = 13.sp,
                                color = Color(0xFF2E3A59)
                            )
                        }
                    }
                }

                // ⏰ Hora
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Hoje",
                        fontSize = 13.sp,
                        color = Color(0xFF2E3A59)
                    )
                    Text(
                        text = timeLabel,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF2E3A59)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 📸 Imagem da publicação
            Image(
                painter = painterResource(id = postImage),
                contentDescription = "Publicação de $playerName",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 📝 Texto da publicação
            Text(
                text = postText,
                fontSize = 15.sp,
                color = Color(0xFF2E3A59)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 💬 Ícones de interação
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ChatBubbleOutline,
                        contentDescription = "Comentários",
                        tint = Color(0xFF2E3A59),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$comments", fontSize = 14.sp, color = Color(0xFF2E3A59))
                }

                Spacer(modifier = Modifier.width(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Curtidas",
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$likes", fontSize = 14.sp, color = Color(0xFFD32F2F))
                }
            }
        }
    }
}
