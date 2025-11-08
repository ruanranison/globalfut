package com.example.globalfut.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavHostController

@Composable
fun BottomBar(
    navController: NavHostController,
    onAddClick: () -> Unit
) {
    val tabs = listOf(Screen.Home, Screen.Players, Screen.Teams, Screen.Profile)
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        // 🔹 Container com sombra e bordas arredondadas reais
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 12.dp,
                    shape = shape,
                    clip = false
                )
                .clip(shape) // mantém o arredondamento do fundo
                .background(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.98f),
                    shape = shape
                )
        ) {
            // 🔹 NavigationBar agora transparente para respeitar as bordas do container
            NavigationBar(
                containerColor = Color.Transparent,
                tonalElevation = 0.dp
            ) {
                tabs.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = { navController.navigate(screen.route) },
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) }
                    )
                    if (index == 1) Spacer(modifier = Modifier.width(56.dp))
                }
            }
        }

        // 🔹 Botão central flutuante com sombra e posição elevada
        FloatingActionButton(
            onClick = onAddClick,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (8).dp) // sobe o botão pra "invadir" a barra
        ) {
            Icon(
                imageVector = Screen.Add.icon,
                contentDescription = "Adicionar",
                modifier = Modifier.size(32.dp) // ⬅️ aumenta o tamanho do ícone
            )
        }
    }
}
