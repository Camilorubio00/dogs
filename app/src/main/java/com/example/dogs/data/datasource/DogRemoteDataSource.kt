package com.example.dogs.data.datasource

import com.example.dogs.data.DogApi
import com.example.dogs.data.model.toDogs
import com.example.dogs.exceptions.ApiExceptionHandler
import com.example.dogs.extensions.Result.Success
import com.example.dogs.extensions.Result.Error
import javax.inject.Inject

class DogRemoteDataSource @Inject constructor(
    private val dogApi: DogApi,
    private val apiExceptionHandler: ApiExceptionHandler
) {
    suspend fun fetchDogs() = try {
        val result = dogApi.fetchDogs()
        Success(result.toDogs())
    } catch (e: Exception) {
        Error(apiExceptionHandler.cause(e))
    }
}