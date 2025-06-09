package com.example.dogs.domain

import com.example.dogs.assertThatEquals
import com.example.dogs.data.repository.DogRepository
import com.example.dogs.exceptions.ApiRequestException.ConnectionNetwork
import com.example.dogs.extensions.Result
import com.example.dogs.fakedata.givenDogs
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FetchDogsUseCaseShould {

    private val dogRepository = mock<DogRepository>()

    private lateinit var fetchDogsUseCase: FetchDogsUseCase

    @Before
    fun setup() {
        fetchDogsUseCase = FetchDogsUseCase(dogRepository)
    }

    @Test
    fun `Get Dogs when fetchDogs is called`(): Unit = runBlocking {
        val resultSuccess = Result.Success(givenDogs())
        whenever(dogRepository.fetchDogs()).thenReturn(flowOf(resultSuccess))

        val result = fetchDogsUseCase().lastOrNull()

        verify(dogRepository).fetchDogs()
        assertThatEquals(result, resultSuccess)
    }

    @Test
    fun `Get Exception when fetchDogs response is failure`() = runBlocking {
        val resultError = Result.Error(ConnectionNetwork())

        whenever(dogRepository.fetchDogs()).thenReturn(flowOf(resultError))

        val result = fetchDogsUseCase().lastOrNull()

        verify(dogRepository).fetchDogs()
        assertThatEquals(result, resultError)
    }
}