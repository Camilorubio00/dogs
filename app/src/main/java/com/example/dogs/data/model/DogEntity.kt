package com.example.dogs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dogs.domain.model.Dog
import com.example.dogs.extensions.ZERO

@Entity(tableName = "dogs")
data class DogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = ZERO,
    val dogName: String,
    val description: String,
    val age: Int,
    val image: String
)

fun List<DogEntity>.toDogList() = map { it.toDog() }

fun DogEntity.toDog() = Dog(
    dogName = dogName,
    description = description,
    age = age,
    image = image
)