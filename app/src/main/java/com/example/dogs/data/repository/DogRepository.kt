package com.example.dogs.data.repository

import com.example.dogs.data.datasource.DogRemoteDataSource
import javax.inject.Inject

class DogRepository @Inject constructor(private val dogRemoteDataSource: DogRemoteDataSource) {
    suspend fun fetchDogs() = dogRemoteDataSource.fetchDogs()
}