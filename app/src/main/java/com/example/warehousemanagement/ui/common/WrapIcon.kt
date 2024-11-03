package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.warehousemanagement.R

@Composable
fun WrapIcon(
    idIcon: Int,
    modifier: Modifier = Modifier,
    tint: Color = colorResource(id = R.color.icon_tint_gray),
    isNewNotification: Boolean = false,
    onClickIcon: () -> Unit = {},
) {
    Box(modifier = modifier.clickable { onClickIcon() }) {
        if (isNewNotification) {
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
            tint = tint,
            painter = painterResource(id = idIcon),
            contentDescription = "",
            // modifier = Modifier.size(size = Dimens.SIZE_ICON_25_DP)
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