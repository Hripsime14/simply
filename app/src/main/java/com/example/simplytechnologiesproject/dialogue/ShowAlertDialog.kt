package com.example.simplytechnologiesproject

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.simplytechnologiesproject.dialogue.AlarmDialogState

@Composable
fun ShowAlertDialog(state: AlarmDialogState, onButtonClick: (Boolean) -> Unit) {
    if (state == AlarmDialogState.Show) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(text = stringResource(R.string.are_you_sure))
            },
            text = {
                Text(text = stringResource(R.string.this_action_will_unlock))
            },
            textContentColor = colorResource(id = R.color.black),
            confirmButton = {
                Button(
                    onClick = {
                        onButtonClick(true)
                    }
                ) {
                    Text(text = stringResource(R.string.yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onButtonClick(false)
                    }
                ) {
                    Text(text = stringResource(R.string.no))
                }
            }
        )
    }
}