package com.example.dogs.ui.model

import com.example.dogs.domain.model.Dog

sealed class DogUiModelState {
    data object Loading : DogUiModelState()
    data class Success(val dogs: List<DogUi>) : DogUiModelState()
    data class Error(val error: Throwable) : DogUiModelState()
}

data class DogUi(
    val dogName: String,
    val description: String,
    val age: Int,
    val image: String
)

fun List<Dog>.toDogUiList() = map { it.toDogUi() }

private fun Dog.toDogUi() = DogUi(
    dogName = dogName,
    description = description,
    age = age,
    image = image
)