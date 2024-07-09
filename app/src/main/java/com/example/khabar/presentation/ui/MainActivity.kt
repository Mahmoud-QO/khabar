package com.example.khabar.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.khabar.presentation.ui.screen.onboarding.OnboardingScreen
import com.example.khabar.presentation.ui.theme.KhabarTheme
import com.example.khabar.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity()
{
    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        installSplashScreen().setKeepOnScreenCondition { viewModel.splashScreenCondition }

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)

            KhabarTheme(windowSizeClass.widthSizeClass, windowSizeClass.heightSizeClass) {
                val navController = rememberNavController()

                Log.d("#####", "onCreate s: ${viewModel.splashScreenCondition}")
                Log.d("#####", "onCreate o: ${viewModel.isOnboardingCompleted}")
                val startDestination = if (viewModel.isOnboardingCompleted)
                    Route.HomeScreen else Route.OnboardingScreen

                NavHost(navController = navController, startDestination = startDestination) {
                    composable<Route.OnboardingScreen> {
                        OnboardingScreen(navController)
                    }

                    composable<Route.HomeScreen> {
                        Text(text = "Hello World!")
                    }
                }
            }
        }
    }
}

