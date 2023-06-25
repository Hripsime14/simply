package com.example.simplytechnologiesproject

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.simplytechnologiesproject.actiontype.ActionType

@Composable
fun HorizontalList(actionList: List<ActionListItem>, onClick: (ActionType) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        items(actionList) { item ->
            CreateListItem(item) {
                onClick(it)
            }
        }
    }
}