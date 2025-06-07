package com.example.dogs.data

import com.example.dogs.assertThatEquals
import com.example.dogs.assertThatIsInstanceOf
import com.example.dogs.data.datasource.DogRemoteDataSource
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

class DogRepositoryShould {

    private val dogRemoteDataSource = mock<DogRemoteDataSource>()

    private lateinit var dogRepository: DogRepository

    @Before
    fun setup() {
        dogRepository = DogRepository(dogRemoteDataSource)
    }

    @Test
    fun `Get Dogs when fetchDogs is called`(): Unit = runBlocking {
        val dogs = givenDogs()
        whenever(dogRemoteDataSource.fetchDogs()).thenReturn(Result.Success(dogs))

        val result = dogRepository.fetchDogs().success()

        verify(dogRemoteDataSource).fetchDogs()
        assertThatEquals(result, dogs)
    }

    @Test
    fun `Get Exception when fetchDogs response is failure`() = runBlocking {
        whenever(dogRemoteDataSource.fetchDogs()).thenReturn(Result.Error(ConnectionNetwork()))

        val result = dogRepository.fetchDogs().error()

        verify(dogRemoteDataSource).fetchDogs()
        assertThatIsInstanceOf<ConnectionNetwork>(result)
    }
}