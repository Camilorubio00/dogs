package com.example.dogs.data.repository

import com.example.dogs.data.datasource.DogLocalDataSource
import com.example.dogs.data.datasource.DogRemoteDataSource
import com.example.dogs.extensions.Result
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val dogRemoteDataSource: DogRemoteDataSource,
    private val dogLocalDataSource: DogLocalDataSource
) {
    fun fetchDogs() = flow {
        val localDogsFlow = dogLocalDataSource.fetchDogs()
        localDogsFlow.first().ifEmpty {
            when (val result = dogRemoteDataSource.fetchDogs()) {
                is Result.Success -> {
                    dogLocalDataSource.insertDogs(result.data)
                }
                is Result.Error -> {
                    emit(result)
                    return@flow
                }
            }
        }
        emitAll(localDogsFlow.map { Result.Success(it) })
    }
}