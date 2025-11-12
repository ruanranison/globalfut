package com.example.globalfut.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GFButtonTabs(
    tabs: List<String>,
    modifier: Modifier = Modifier,
    initialSelectedIndex: Int = 0,
    onTabSelected: (index: Int) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(initialSelectedIndex) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEachIndexed { index, tab ->
            Text(
                text = tab,
                fontSize = 14.sp,
                color = if (index == selectedIndex) Color.Black else Color.Gray,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        selectedIndex = index
                        onTabSelected(index)
                    }
            )
        }
    }
}
