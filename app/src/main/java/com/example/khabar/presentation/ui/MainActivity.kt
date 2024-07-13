package com.example.khabar.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.khabar.presentation.ui.screen.onboarding.OnboardingScreen
import com.example.khabar.presentation.ui.theme.KhabarTheme
import com.example.khabar.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity()
{
    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()

            KhabarTheme(windowSizeClass.widthSizeClass, windowSizeClass.heightSizeClass) {

                val splashScreenCondition by viewModel.splashScreenCondition.collectAsState()
                val isOnboardingCompleted by viewModel.isOnboardingCompleted.collectAsState()

                val startDestination = if (isOnboardingCompleted)
                    Route.HomeScreen else Route.OnboardingScreen

                installSplashScreen().setKeepOnScreenCondition { splashScreenCondition }

                NavHost(navController = navController, startDestination = startDestination) {
                    composable<Route.OnboardingScreen> {
                        OnboardingScreen(navController)
                    }

                    composable<Route.HomeScreen> {
                        val lazyPagingItems = viewModel.topHeadlines.collectAsLazyPagingItems()
                        val newsSources by viewModel.newsSources.collectAsState()
                        val articles by remember {
                            derivedStateOf { lazyPagingItems.itemSnapshotList.items }
                        }

                        LazyColumn {
                            items(newsSources) {
                                Text(text = "${it.name} - ${it.id}", color = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}

