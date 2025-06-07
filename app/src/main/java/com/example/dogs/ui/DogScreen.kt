package com.example.dogs.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dogs.ui.composable.CircularProgressIndicatorFixMax
import com.example.dogs.ui.model.DogUi
import com.example.dogs.ui.model.DogUiModelState

@Composable
fun DogScreen(dogUiModelState: DogUiModelState) {
    Scaffold(topBar = { DogTopAppBar() }) {
        DogsUiState(
            dogUiModelState = dogUiModelState,
            paddingValues = it
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DogTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
            Text(
                text = "AppBar",
                style = MaterialTheme.typography.titleMedium
            )
        }
    )
}

@Composable
private fun DogsUiState(
    dogUiModelState: DogUiModelState,
    paddingValues: PaddingValues
) {
    when (dogUiModelState) {
        is DogUiModelState.Loading -> CircularProgressIndicatorFixMax()

        is DogUiModelState.Success -> DogsContent(
            modifier = Modifier.padding(paddingValues = paddingValues),
            dogs = dogUiModelState.dogs
        )

        is DogUiModelState.Error -> Unit
    }
}

@Composable
fun DogsContent(modifier: Modifier = Modifier, dogs: List<DogUi>) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(items = dogs) {
            Text(it.dogName)
        }
    }
}