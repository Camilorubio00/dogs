package com.example.dogs.data.datasource

import com.example.dogs.data.DogApi
import javax.inject.Inject

class DogRemoteDataSource @Inject constructor(private val dogApi: DogApi) {
    suspend fun fetchDogs() = dogApi.fetchDogs()
}