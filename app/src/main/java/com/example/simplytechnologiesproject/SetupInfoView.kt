package com.example.simplytechnologiesproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SetupInfoView() {
    Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {
        Text(text = stringResource(R.string.est_range), color = colorResource(id = R.color.gray), fontWeight = FontWeight.SemiBold)
        Row {
            Text(text = "120", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Text(text = " mi", fontSize = 18.sp, modifier = Modifier.align(Alignment.Bottom).padding(bottom = 6.dp))
        }
        Row(modifier = Modifier.padding(top = 15.dp)) {
            Image(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.weather),
                contentDescription = "Image")
            Text(text = stringResource(R.string.weather_info), fontWeight = FontWeight.SemiBold)
        }
    }
}