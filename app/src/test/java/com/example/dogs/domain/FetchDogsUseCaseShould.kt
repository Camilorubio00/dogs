package com.example.dogs.domain

import com.example.dogs.assertThatEquals
import com.example.dogs.assertThatIsInstanceOf
import com.example.dogs.data.repository.DogRepository
import com.example.dogs.exceptions.ApiRequestException.ConnectionNetwork
import com.example.dogs.extensions.Result
import com.example.dogs.extensions.error
import com.example.dogs.extensions.success
import com.example.dogs.fakedata.givenDogs
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
        val dogs = givenDogs()
        whenever(dogRepository.fetchDogs()).thenReturn(Result.Success(dogs))

        val result = fetchDogsUseCase().success()

        verify(dogRepository).fetchDogs()
        assertThatEquals(result, dogs)
    }

    @Test
    fun `Get Exception when fetchDogs response is failure`() = runBlocking {
        whenever(dogRepository.fetchDogs()).thenReturn(Result.Error(ConnectionNetwork()))

        val result = fetchDogsUseCase().error()

        verify(dogRepository).fetchDogs()
        assertThatIsInstanceOf<ConnectionNetwork>(result)
    }
}