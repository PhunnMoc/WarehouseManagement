import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R

@Composable
fun MiniButton(
    onClick: () -> Unit,
    icon: Painter,
    contentDescription: String? = null
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .width(80.dp)
            .height(80.dp),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = icon,
            contentDescription = contentDescription
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMiniButton() {
    MiniButton(
        onClick = { /* Handle button click */ },
        icon = painterResource(id = R.drawable.ic_add_mini_button),
        contentDescription = "Add Icon"
    )
}