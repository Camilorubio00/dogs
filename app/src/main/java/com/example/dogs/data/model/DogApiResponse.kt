package com.example.dogs.data.model

import com.example.dogs.domain.model.Dog

data class DogDto(
    val dogName: String? = null,
    val description: String? = null,
    val age: String? = null,
    val image: String? = null
)

fun List<DogDto>.toDogs() = map { it.toDog() }

fun DogDto.toDog() = Dog(
    dogName = dogName.orEmpty(),
    description = description.orEmpty(),
    age = age.orEmpty(),
    image = image.orEmpty()
)