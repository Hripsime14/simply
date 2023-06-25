package com.example.simplytechnologiesproject.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplytechnologiesproject.*
import com.example.simplytechnologiesproject.actiontype.ActionType
import com.example.simplytechnologiesproject.dialogue.AlarmDialogState
import com.example.simplytechnologiesproject.snackbar.AppSnackbarState
import com.example.simplytechnologiesproject.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    colorId: Int,
    drawableBackgroundId: Int,
    drawableCarId: Int
) {
    val list = viewModel.actionItemListFlow.collectAsState()
    val dialogState = viewModel.alertDialogFlow.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val lifeCycle = LocalLifecycleOwner.current.lifecycle
    val actionType = rememberSaveable {
        mutableStateOf<ActionType?>(null)
    }

    LaunchedEffect(Unit) {
        lifeCycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.snackBarFlow.collectLatest {
                when (it) {
                    is AppSnackbarState.Show -> snackbarHostState.showSnackbar(
                        it.text,
                        duration = it.duration
                    )
                    else -> snackbarHostState.currentSnackbarData?.dismiss()
                }
            }
        }
    }

    Scaffold(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 100.dp),

        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 50.dp),
                    color = colorResource(id = colorId)
                ) {
                    Row(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(16.dp)
                    ) {
                        HorizontalList(list.value) {
                            actionType.value = it
                            if (actionType.value == ActionType.UNLOCK) {
                                viewModel.changeAlertDialogState(AlarmDialogState.Show)
                            } else {
                                viewModel.changeSelection(it) // change state without opening dialog
                            }
                        }

                    }
                }
            }

            ShowAlertDialog(dialogState.value) { sure ->
                actionType.value?.let {
                    if (sure) {
                        viewModel.showOrHideSnackbar(
                            AppSnackbarState.Show(
                                "Waking Ariya to unlock...",
                                SnackbarDuration.Indefinite
                            )
                        )
                        viewModel.showAndHideProgressBarWithDelay(5000L)
                    }
                }
                viewModel.changeAlertDialogState(AlarmDialogState.Hide)

            }

            if (isLoading.value == LoadingState.Loading) {
                CircularProgressIndicator()
            } else if (isLoading.value == LoadingState.NotLoading) {

                actionType.value?.let { type ->
                    viewModel.changeSelection(type)
                }
            }
        }
    }
}

@Composable
fun CircularProgressIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp),
            color = Color.Gray,
            strokeWidth = 3.dp
        )
    }
}