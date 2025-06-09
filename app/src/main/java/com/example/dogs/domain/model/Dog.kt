package com.example.dogs.domain.model

import com.example.dogs.data.model.DogEntity

data class Dog(
    val dogName: String,
    val description: String,
    val age: Int,
    val image: String
)

fun List<Dog>.toDogEntityList() = map { it.toDogEntity() }

fun Dog.toDogEntity() = DogEntity(
    dogName = dogName,
    description = description,
    age = age,
    image = image
)