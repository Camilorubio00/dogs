package com.example.dogs.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import com.example.dogs.R
import com.example.dogs.extensions.MaxLine1
import com.example.dogs.extensions.MaxLine3
import com.example.dogs.extensions.Space12
import com.example.dogs.extensions.Space150
import com.example.dogs.extensions.Space16
import com.example.dogs.extensions.Space180
import com.example.dogs.extensions.Space200
import com.example.dogs.extensions.Space24
import com.example.dogs.extensions.Space8
import com.example.dogs.ui.composable.CircularProgressIndicatorFixMax
import com.example.dogs.ui.model.DogUi
import com.example.dogs.ui.model.DogUiModelState
import com.example.dogs.ui.theme.BackgroundGrayColor

@Composable
fun DogScreen(dogUiModelState: DogUiModelState, onBackClick: () -> Unit) {
    Scaffold(modifier = Modifier.background(BackgroundGrayColor), topBar = { DogTopAppBar(onBackClick) }) {
        DogsUiState(
            dogUiModelState = dogUiModelState,
            paddingValues = it
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DogTopAppBar(onBackClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.app_bar_title),
                    style = MaterialTheme.typography.titleLarge
                )
            }
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
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Space16)
    ) {
        items(items = dogs) {
            DogsItem(dog = it)
        }
    }
}

@Composable
fun DogsItem(dog: DogUi) = dog.run {
    Row(modifier = Modifier.padding(vertical = Space8)) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(width = Space150, height = Space200)
                .clip(RoundedCornerShape(Space12))
        )
        Column(
            modifier = Modifier
                .height(Space180)
                .fillMaxWidth()
                .align(Alignment.Bottom)
                .background(
                    Color.White,
                    RoundedCornerShape(
                        topEnd = Space12,
                        bottomEnd = Space12
                    )
                )
                .padding(Space24)
        ) {
            Text(
                text = dogName,
                maxLines = MaxLine1,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = description,
                maxLines = MaxLine3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = Space8)
            )
            Spacer(modifier = Modifier.height(Space12))
            Text(
                text = stringResource(R.string.almost_years, age),
                maxLines = MaxLine1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}