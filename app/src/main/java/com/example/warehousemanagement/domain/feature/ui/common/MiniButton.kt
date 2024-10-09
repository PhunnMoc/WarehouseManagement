import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R

@Composable
fun MiniButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .width(80.dp)  // Width of the button
            .height(80.dp),
    ) {
        Image(

            modifier = Modifier.fillMaxSize(),

            painter = painterResource(id = R.drawable.icons8_add_button_80),
            contentDescription = "Add Icon"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMiniButton() {
    MiniButton(onClick = { /*TODO: Handle button click*/ })
}