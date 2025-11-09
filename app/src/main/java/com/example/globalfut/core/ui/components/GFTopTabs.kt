import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.globalfut.core.ui.theme.*

@Composable
fun GFButtonTabs(
    tabs: List<String>,
    modifier: Modifier = Modifier,
    onTabSelected: (Int) -> Unit = {}
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val scrollState = rememberScrollState()

    Row(
        modifier = modifier
            .horizontalScroll(scrollState)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tabs.forEachIndexed { index, title ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(36.dp)
                    .width(96.dp)
                    .border(
                        width = 1.dp,
                        color = GFPrimary,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = if (selectedTabIndex == index) GFPrimary else Color.Transparent,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {
                        selectedTabIndex = index
                        onTabSelected(index)
                    }
            ) {
                Text(
                    text = title,
                    color = if (selectedTabIndex == index) Color.White else GFPrimary,
                    fontSize = 14.sp,
                )
            }
        }
    }


}
