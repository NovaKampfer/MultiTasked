package com.example.multitasked

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.multitasked.data.repository.AuthRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.example.multitasked.ui.navigation.CrowdTasksNavHost
import com.example.multitasked.ui.navigation.Routes
import com.example.multitasked.ui.theme.AppTheme
import com.example.multitasked.ui.theme.MultiTaskedTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MultiTaskedApp(authRepository = authRepository)
        }
    }
}

@Composable
fun MultiTaskedApp(authRepository: AuthRepository) {
    // Persist selected theme across recompositions & config changes
    var currentTheme by rememberSaveable { mutableStateOf(AppTheme.SYSTEM) }

    MultiTaskedTheme(theme = currentTheme) {
        val navController = rememberNavController()

        val startDestination = if (authRepository.currentUserId() != null) {
            Routes.BOARDS
        } else {
            Routes.AUTH
        }

        CrowdTasksNavHost(
            navController = navController,
            startDestination = startDestination,
            currentTheme = currentTheme,
            onThemeChange = { newTheme: AppTheme ->
                currentTheme = newTheme
            }
        )
    }
}
