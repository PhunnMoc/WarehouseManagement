package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.warehousemanagement.R

@Composable
fun WrapIcon(
    idIcon: Int,
    isNewNotification: Boolean = false,
) {
    Box {
       if(isNewNotification){
           Box(
               modifier = Modifier
                   .offset(3.dp, 3.dp)
                   .size(10.dp)
                   .zIndex(1f)
                   .clip(RoundedCornerShape(10.dp))
                   .background(colorResource(id = R.color.background_theme))
           )
       }
        Icon(
            painter = painterResource(id = idIcon),
            contentDescription = ""
        )
    }

}

@Preview
@Composable
fun WrapIconPreview() {
    WrapIcon(
        idIcon = R.drawable.icons8_bell,
    )
}
@Preview
@Composable
fun WrapIconNotificationPreview() {
    WrapIcon(
        idIcon = R.drawable.icons8_bell,
        isNewNotification = true,
    )
}