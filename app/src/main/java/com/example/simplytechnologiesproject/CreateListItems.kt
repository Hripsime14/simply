package com.example.simplytechnologiesproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplytechnologiesproject.actiontype.ActionType

@Composable
fun CreateListItem(item: ActionListItem, onClick: (ActionType) -> Unit) {
    val myBoolean = rememberSaveable {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.clickable {
        item.actionType?.let {
            onClick(it)
        }
    }) {
        val (color, colorFilter) = if (item.selected) {
            Color.Gray to ColorFilter.tint(Color.Gray)
        } else {
            Color.Black to ColorFilter.tint(Color.Black)
        }
//        if (myBoolean.value) {
//            AlertDialogSample {
//
//            }
//        }
        Box(
            modifier = Modifier
                .size(60.dp)
                .border(
                    width = 2.dp,
                    color = color,
                    shape = CircleShape
                )
        ) {
            Image(
                painter = painterResource(id = item.drawableId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                contentScale = ContentScale.Fit,
                colorFilter = colorFilter
            )
        }
        Text(
            text = stringResource(id = item.stringId),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 12.sp
        )
    }
}