package com.example.dogs.fakedata

import com.example.dogs.domain.model.Dog
import com.example.dogs.ui.model.DogUi

private const val ANY_DOG_NAME = "Rex"
private const val ANY_DESCRIPTION = "He is much more passive and is the first to suggest to rescue and not eat The Little Pilot"
private const val ANY_AGE = 5
private const val ANY_IMAGE = "https://static.wikia.nocookie.net/isle-of-dogs/images/a/af/Rex.jpg/revision/latest/scale-to-width-down/666?cb=20180625001634"

fun givenDogs() = listOf(givenDog())

private fun givenDog() = Dog(
    dogName = ANY_DOG_NAME,
    description = ANY_DESCRIPTION,
    age = ANY_AGE,
    image = ANY_IMAGE
)

fun givenDogUiList() = listOf(givenDogUi())

private fun givenDogUi() = DogUi(
    dogName = ANY_DOG_NAME,
    description = ANY_DESCRIPTION,
    age = ANY_AGE,
    image = ANY_IMAGE)