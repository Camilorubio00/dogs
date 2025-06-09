package com.example.dogs.data.model

import com.example.dogs.domain.model.Dog
import com.example.dogs.extensions.orDefault

data class DogDto(
    val dogName: String? = null,
    val description: String? = null,
    val age: Int? = null,
    val image: String? = null
)

fun List<DogDto>.toDogList() = map { it.toDog() }

fun DogDto.toDog() = Dog(
    dogName = dogName.orEmpty(),
    description = description.orEmpty(),
    age = age.orDefault(),
    image = image.orEmpty()
)