package com.example.dogs.ui.composable

import android.annotation.SuppressLint
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import com.example.dogs.ui.theme.TextColorWhite
import kotlinx.coroutines.launch

@Composable
fun SnackBarError(snackBarHostState: SnackbarHostState) {
    SnackbarHost(hostState = snackBarHostState) {
        Snackbar(
            snackbarData = it,
            containerColor = Color.Red,
            contentColor = TextColorWhite
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LaunchSnackBar(snackBarHostState: SnackbarHostState, message: String) {
    val scope = rememberCoroutineScope()
    scope.launch {
        snackBarHostState.showSnackbar(message = message)
    }
}