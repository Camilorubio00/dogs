package com.example.dogs.data.datasource

import com.example.dogs.data.model.toDogList
import com.example.dogs.domain.model.Dog
import com.example.dogs.domain.model.toDogEntityList
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DogLocalDataSource @Inject constructor(private val dogDao: DogDao) {
    fun fetchDogs() = dogDao.fetchDogs().map { it.toDogList() }
    fun insertDogs(dogs: List<Dog>) = dogDao.insertDogs(dogs.toDogEntityList())
}