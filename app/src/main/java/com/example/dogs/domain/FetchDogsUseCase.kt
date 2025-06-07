package com.example.dogs.domain

import com.example.dogs.data.repository.DogRepository
import javax.inject.Inject

class FetchDogsUseCase @Inject constructor(private val dogRepository: DogRepository) {
    suspend operator fun invoke() = dogRepository.fetchDogs()
}