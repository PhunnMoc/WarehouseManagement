package com.example.warehousemanagement.ui.common
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Notification
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NotificationCard(
    modifier: Modifier = Modifier,
    notification: Notification,
    onCardClick: () -> Unit
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    Card(
        backgroundColor = Color.White,
        modifier = modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
            .animateContentSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    isExpanded = !isExpanded
                    onCardClick()
                }, onLongPress = {
                    // Handle long press
                    showDialog = true
                })
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Header Row with Title and Type
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.background_gray))
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = notification.title,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.text_color_light_black)
                )
                Text(
                    text = notification.timestamp.toString(),
                    fontSize = 14.sp,
                )
            }
            Divider()

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = "Description: ${notification.description}", fontSize = 14.sp, color = Color.Gray
                )
                Text(
                    text = "Type: ${notification.type}", fontSize = 14.sp, color = Color.Gray
                )
            }
        }
    }

}

val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
val notification1 = Notification(
    id = "67276a79a0b1c2534dca6e72",
    title = "Security Warning",
    description = "A potential security threat has been detected.",
    type = "WARNING",
    timestamp = dateFormat.parse("2023-11-07T08:00:00.000Z") ?: Date()
)

@Preview(showBackground = true)
@Composable
fun PreviewNotificationCard() {
    NotificationCard(notification = notification1, onCardClick = { /* Handle card click here */ })
}
