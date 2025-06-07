package com.example.dogs.data

import com.example.dogs.data.model.DogDto
import retrofit2.http.GET

interface DogApi {

    @GET(DOGS_ENDPOINT)
    suspend fun fetchDogs(): List<DogDto>
}