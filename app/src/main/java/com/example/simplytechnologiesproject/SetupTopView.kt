package com.example.simplytechnologiesproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SetupTopView() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        val imagePainter = painterResource(id = R.drawable.support)
        val colorFilter = ColorFilter.tint(Color.Black)
        Image(
            modifier = Modifier,
            painter = imagePainter,
            contentDescription = "Image",
            colorFilter = colorFilter
        )
        Row {
            Image(
                modifier = Modifier.align(CenterVertically),
                painter = painterResource(id = R.drawable.lock),
                contentDescription = "Image",
                colorFilter = colorFilter
            )
            Text(text = stringResource(R.string.my_nissan_ariya))
        }
        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.notifications),
            contentDescription = "Image",
            colorFilter = colorFilter
        )
    }
}