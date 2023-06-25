package com.example.simplytechnologiesproject.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplytechnologiesproject.*
import com.example.simplytechnologiesproject.actiontype.ActionType
import com.example.simplytechnologiesproject.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(),
        colorId: Int,
        drawableBackgroundId: Int,
        drawableCarId: Int,
) {
    val list = viewModel.actionItemListFlow.collectAsState()
    val dialogState = viewModel.alertDialogFlow.collectAsState()
    val actionType = rememberSaveable {
        mutableStateOf<ActionType?>(null)
    }
    Surface(color = colorResource(id = colorId)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = drawableBackgroundId),
                contentDescription = "Top Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    SetupTopView()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        SetupInfoView()
                        Image(
                            painter = painterResource(id = drawableCarId),
                            contentDescription = "Image",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = 100.dp)
                        )
                    }
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colorResource(id = colorId)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(16.dp)
                ) {
                    HorizontalList(list.value) {
                        actionType.value = it
                        if (actionType.value == ActionType.Unlock) {
                            viewModel.changeAlertDialogState(AlarmDialogState.Show)
                        } else {
                            viewModel.changeSelection(it) // change state without opening dialog
                        }
                    }
                }
            }

            ShowAlertDialog(dialogState.value) {sure ->
                actionType.value?.let {
                    if (sure)
                    viewModel.changeSelection(it)
                }
                viewModel.changeAlertDialogState(AlarmDialogState.Hide)
            }
        }
    }
}