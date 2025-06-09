package com.example.dogs.data

import com.example.dogs.MockWebServerRule
import com.example.dogs.assertThatEquals
import com.example.dogs.assertThatIsInstanceOf
import com.example.dogs.data.datasource.DogRemoteDataSource
import com.example.dogs.exceptions.ApiExceptionHandler
import com.example.dogs.exceptions.ApiExceptionHandler.Companion.AUTHENTICATION_CODE
import com.example.dogs.exceptions.ApiRequestException
import com.example.dogs.extensions.error
import com.example.dogs.extensions.success
import com.example.dogs.fakedata.givenDogList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.create

class DogRemoteDataSourceShould {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    private val apiExceptionHandler = ApiExceptionHandler()

    private lateinit var dogRemoteDataSource: DogRemoteDataSource

    @Before
    fun setup() {
        val configurationRetrofit = mockWebServerRule.configurationRetrofit()

        dogRemoteDataSource = DogRemoteDataSource(
            dogApi = configurationRetrofit.create(),
            apiExceptionHandler = apiExceptionHandler)
    }

    @Test
    fun `Verify GET method when fetchDogs is called`() = runBlocking {
        mockWebServerRule.givenMockResponse()

        dogRemoteDataSource.fetchDogs()

        mockWebServerRule.assertGetRequestSentTo("/1151549092634943488")
    }

    @Test
    fun `Get dog list when fetchDogs is called`() = runBlocking {
        val dogList = givenDogList()
        mockWebServerRule.givenMockResponse(fileName = "endpoints-responses/fetch-dogs-response.json")

        val result = dogRemoteDataSource.fetchDogs().success()

        assertThatEquals(result, dogList)
    }

    @Test
    fun `Return Api Request Exception when fetchDogs throws connection error occurred`() = runBlocking {
        mockWebServerRule.givenMockResponse(responseCode = AUTHENTICATION_CODE)

        val result = dogRemoteDataSource.fetchDogs().error()

        assertThatIsInstanceOf<ApiRequestException>(result)
    }
}