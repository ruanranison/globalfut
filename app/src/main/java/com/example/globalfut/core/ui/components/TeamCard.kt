package com.example.globalfut.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.globalfut.core.ui.theme.GFPrimary


@Composable
fun TeamCard(
    name: String,
    city: String,
    posts: Int,
    age: Int,
    photoUrl: String,
    onDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            AsyncImage(
                model = photoUrl,
                contentDescription = "Foto do jogador",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E3A59)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
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

                Spacer(modifier = Modifier.height(12.dp))

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
}