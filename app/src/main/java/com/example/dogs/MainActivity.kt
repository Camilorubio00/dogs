package com.example.dogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.dogs.ui.DogScreen
import com.example.dogs.ui.theme.DogsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InitContentWithUiState(mainActivityViewModel)
                }
            }
        }
        mainActivityViewModel.fetchDogs()
    }
}

@Composable
private fun InitContentWithUiState(mainActivityViewModel: MainActivityViewModel) {
    val dogsUiModelState by mainActivityViewModel.dogsUiModelState.collectAsState()
    dogsUiModelState?.run {
        val activity = LocalActivity.current as? ComponentActivity
        DogScreen(dogUiModelState = this, onBackClick = { activity?.finish() })
    }
}
