package com.example.simplytechnologiesproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.simplytechnologiesproject.ui.theme.SimplyTechnologiesProjectTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() {
            SimplyTechnologiesProjectTheme(darkTheme = false) {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }

    @Composable
    fun MyApp(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
        ) {
            SetupBottomNavigation()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        SimplyTechnologiesProjectTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
            ) {
                SetupBottomNavigation()
            }
        }
    }
}