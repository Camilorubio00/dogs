package com.example.dogs.ui

import com.example.dogs.MainActivityViewModel
import com.example.dogs.domain.FetchDogsUseCase
import com.example.dogs.TestDispatcherRule
import com.example.dogs.assertThatEquals
import com.example.dogs.assertThatIsInstanceOf
import com.example.dogs.exceptions.ApiRequestException.ConnectionNetwork
import com.example.dogs.extensions.Result
import com.example.dogs.fakedata.givenDogList
import com.example.dogs.fakedata.givenDogUiList
import com.example.dogs.ui.model.DogUiModelState
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MainActivityViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val fetchDogsUseCase = mock<FetchDogsUseCase>()

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Before
    fun setup() {
        mainActivityViewModel = MainActivityViewModel(fetchDogsUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @Test
    fun `get dogUiList from DogUiModelState when fetchDogs is called and fetchDogsUseCase is success`() = runTest {
        val dogList = givenDogList()
        val dogUiList = givenDogUiList()
        val resultSuccess = Result.Success(dogList)

        whenever(fetchDogsUseCase()).thenReturn(flowOf(resultSuccess))

        mainActivityViewModel.fetchDogs()

        val result = mainActivityViewModel.dogsUiModelState.firstOrNull()

        verify(fetchDogsUseCase).invoke()
        assertThatIsInstanceOf<DogUiModelState.Success>(result)
        assertThatEquals((result as DogUiModelState.Success).dogs, dogUiList)
    }

    @Test
    fun `Get Exception from DogUiModelState when fetchDogs is called and fetchDogsUseCase is failure`() = runTest {
        val resultError = Result.Error(ConnectionNetwork())
        whenever(fetchDogsUseCase()).thenReturn(flowOf(resultError))

        mainActivityViewModel.fetchDogs()

        val result = mainActivityViewModel.dogsUiModelState.firstOrNull()

        verify(fetchDogsUseCase).invoke()
        assertThatIsInstanceOf<ConnectionNetwork>((result as DogUiModelState.Error).error)
    }
}