package com.dguzman.todoapp.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun ActionIcon(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    icon: ImageVector,
    contentDescription: String? = null,
    tint: Color = Color.White,
    size: Dp,
    onClick: () -> Unit = {  },

    ) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(backgroundColor).size(size)




    ) {

        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}
