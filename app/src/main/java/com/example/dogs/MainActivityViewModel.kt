package com.example.dogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.core.CoroutinesDispatchers
import com.example.dogs.domain.FetchDogsUseCase
import com.example.dogs.domain.model.Dog
import com.example.dogs.extensions.Result
import com.example.dogs.ui.model.DogUiModelState
import com.example.dogs.ui.model.toDogUiList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fetchDogsUseCase: FetchDogsUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _dogsUiModelState = MutableStateFlow<DogUiModelState?>(null)

    val dogsUiModelState: StateFlow<DogUiModelState?>
        get() = _dogsUiModelState

    fun fetchDogs() {
        emitDogsUiState(DogUiModelState.Loading)
        viewModelScope.launch(coroutinesDispatchers.io) {
            fetchDogsUseCase().collect {
                when (it) {
                    is Result.Success -> emitDogsUiStateSuccess(it.data)
                    is Result.Error -> emitDogsUiStateError(it.exception)
                }
            }
        }
    }

    private fun emitDogsUiStateSuccess(dogs: List<Dog>) {
        val dogUiModelStateSuccess = DogUiModelState.Success(dogs.toDogUiList())
        emitDogsUiState(dogUiModelStateSuccess)
    }

    private fun emitDogsUiStateError(throwable: Throwable) {
        val dogUiModelStateError = DogUiModelState.Error(throwable)
        emitDogsUiState(dogUiModelStateError)
    }

    private fun emitDogsUiState(dogUiModelState: DogUiModelState) {
        _dogsUiModelState.value = dogUiModelState
    }
}
