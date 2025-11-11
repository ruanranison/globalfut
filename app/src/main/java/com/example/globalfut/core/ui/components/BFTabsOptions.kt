package com.example.globalfut.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.globalfut.core.ui.theme.GFPrimary

@Composable
fun BFTabsOptions(
    options: List<String> = listOf("Estatísticas", "Publicações"),
    modifier: Modifier = Modifier,
    onOptionSelected: (Int) -> Unit = {}
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(38.dp)
            .border(
                width = 1.dp,
                color = GFPrimary,
                shape = RoundedCornerShape(6.dp)
            )
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        options.forEachIndexed { index, title ->
            val isSelected = index == selectedIndex

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        color = if (isSelected) GFPrimary else Color.Transparent,
                        shape = when (index) {
                            0 -> RoundedCornerShape(topStart = 6.dp, bottomStart = 6.dp)
                            options.lastIndex -> RoundedCornerShape(topEnd = 6.dp, bottomEnd = 6.dp)
                            else -> RoundedCornerShape(0.dp)
                        }
                    )
                    .clickable {
                        selectedIndex = index
                        onOptionSelected(index)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = if (isSelected) Color.White else GFPrimary,
                    fontSize = 14.sp,
                )
            }
        }
    }
}
